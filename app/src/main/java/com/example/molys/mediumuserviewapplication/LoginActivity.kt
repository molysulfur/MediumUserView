package com.example.molys.mediumuserviewapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.molys.mediumuserviewapplication.service.MediumService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var disposable  : Disposable? = null
    private var code : String = ""
    private lateinit var activityIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }

    private fun init() {
        if (intent.data != null) {
            getAccessTokenWithCode()
        }
        btnLogin.setOnClickListener {
            activityIntent = Intent(Intent.ACTION_VIEW)
            activityIntent.data = Uri.parse("https://medium.com/m/oauth/authorize?client_id=${MediumService.CLIENT_ID}&scope=basicProfile,publishPost&state=200&response_type=code&redirect_uri=${MediumService.REDIRECT_URI}")
            startActivity(activityIntent)
        }
    }

    private fun getAccessTokenWithCode() {
        val uri = intent.data
        code = uri!!.getQueryParameter("code")?:""
        disposable = MediumService.getMediumService()
            .getAccessToken(
                code, MediumService.CLIENT_ID,
                MediumService.CLIENT_SECRET,
                MediumService.GRANT_TYPE,
                MediumService.REDIRECT_URI
            ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            if (it != null){
                activityIntent = Intent(this,MainActivity::class.java)
                activityIntent.putExtra("token",it.accessToken)
                startActivity(activityIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}
