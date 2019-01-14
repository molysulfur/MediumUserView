package com.example.molys.mediumuserviewapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.molys.mediumuserviewapplication.adapter.MediumAdapter
import com.example.molys.mediumuserviewapplication.data.DataProfile
import com.example.molys.mediumuserviewapplication.data.DataPublication
import com.example.molys.mediumuserviewapplication.data.Profile
import com.example.molys.mediumuserviewapplication.data.Publication
import com.example.molys.mediumuserviewapplication.service.MediumService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.parceler.Parcels

class MainActivity : AppCompatActivity() {

    private val SAVE_PROFLIE = "SAVE_PROFLIE"
    private val SAVE_PUBLICATION = "SAVE_PUBLICATION"
    private var token = ""
    private var userId = ""
    private var disposable : Disposable? = null
    private var listPublication = ArrayList<Publication>()
    private lateinit var userProfile: Profile
    private lateinit var callService: MediumService

    private val profileObserver = object : Observer<DataProfile> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(profile: DataProfile) {
            userId = profile.data?.id ?: ""
            userProfile = profile.data ?: Profile()
            getPublication().subscribe(publicationObserver)
        }

        override fun onError(e: Throwable) {
        }
    }

    private val publicationObserver = object : Observer<DataPublication> {
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(dataPublication: DataPublication) {
            dataPublication.data?.forEach { publication ->
                isEditorOrWriter(publication)
            }
        }

        override fun onError(e: Throwable) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            init()
        } else {

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SAVE_PROFLIE, Parcels.wrap(userProfile))
        outState.putParcelable(SAVE_PUBLICATION, Parcels.wrap(listPublication))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        userProfile = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_PROFLIE))
        listPublication = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_PUBLICATION))
        recyclerMain.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MediumAdapter(MediumCreator.toBaseItem(userProfile, listPublication))
        }
    }

    private fun init() {
        token = "Bearer " + intent.getStringExtra("token")
        callService = MediumService.getMediumService()

        callService.getProfile(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(profileObserver)
    }

    private fun getPublication(): Observable<DataPublication> {
        return callService.getPublicationsWithClientId(token, userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun isEditorOrWriter(publication: Publication) {
        disposable = callService.getPublicationWithId(token, publication.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { contributors ->
                contributors.data?.forEach { contributor ->
                    if (contributor.userId == userId) {
                        listPublication.add(publication)
                    }
                }
            }
            .doOnComplete {
                recyclerMain.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MediumAdapter(MediumCreator.toBaseItem(userProfile, listPublication))
                }
            }
            .subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
