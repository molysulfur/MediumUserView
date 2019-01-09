package com.example.molys.mediumuserviewapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.molys.mediumuserviewapplication.data.DataContributors
import com.example.molys.mediumuserviewapplication.data.DataProfile
import com.example.molys.mediumuserviewapplication.data.DataPublication
import com.example.molys.mediumuserviewapplication.data.Publication
import com.example.molys.mediumuserviewapplication.service.MediumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    private var token = ""
    private val BASE_URL = "https://api.medium.com/v1/"
    private var userId = ""
    private var listPublication = ArrayList<Publication>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        token = "Bearer " + intent.getStringExtra("token")
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val callService = retrofit.create(MediumService::class.java)
        callService.getProfile(token).enqueue(object : Callback<DataProfile?> {
            override fun onFailure(call: Call<DataProfile?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity,"Error for Get Profile",Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<DataProfile?>, response: Response<DataProfile?>) {
                userId = response.body()!!.data.id
                callService.getPublicationsWithClientId(token,userId).enqueue(object: Callback<DataPublication?> {
                    override fun onFailure(call: Call<DataPublication?>, t: Throwable) {
                        t.printStackTrace()
                        Toast.makeText(this@MainActivity,"Error for Get Publication",Toast.LENGTH_LONG)
                    }

                    override fun onResponse(call: Call<DataPublication?>, response: Response<DataPublication?>) {
                        response.body()!!.data.forEach {
                            isEditorOrWriter(it,callService)
                        }
                    }
                })
            }
        })
    }

    private fun isEditorOrWriter(publication: Publication, callService: MediumService) {
        callService.getPublicationWithId(token,publication.id).enqueue(object: Callback<DataContributors?> {
            override fun onFailure(call: Call<DataContributors?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity,"Error for Get Contributor",Toast.LENGTH_SHORT)
            }

            override fun onResponse(call: Call<DataContributors?>, response: Response<DataContributors?>) {
                response.body()!!.data.forEach {
                    if(it.userId == userId){
                        listPublication.add(publication)
                    }
                    Log.e("response",listPublication.toString())
                }
            }
        })

    }
}