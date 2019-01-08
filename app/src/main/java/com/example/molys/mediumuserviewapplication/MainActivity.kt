package com.example.molys.mediumuserviewapplication

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/m/oauth/authorize?client_id=46e13914780c&scope=basicProfile,publishPost&state=200&response_type=code&redirect_uri=molysulfur://callback"))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        var uri = intent.data
        var token = ""
        if (uri !=null){
            val code = uri.getQueryParameter("code")
            Log.e("code",code)
            val retrofit = Retrofit.Builder().baseUrl("https://api.medium.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            Log.e("code",code)
            val accessTokenService = retrofit.create(OauthService::class.java)
                .getAccessToken(code,"46e13914780c","127218d26fd1c38485fd347c48289c3471933795",
                    "authorization_code","molysulfur://callback")
            accessTokenService.enqueue(object: Callback<AccessToken?> {
                override fun onFailure(call: Call<AccessToken?>, t: Throwable) {
                    Log.e("call",t.toString())
                }

                override fun onResponse(call: Call<AccessToken?>, response: Response<AccessToken?>) {
                    Log.e("call",response.body()!!.accessToken)
                    token = response.body()!!.accessToken
                    val me = retrofit.create(OauthService::class.java)
                        .getMe("Bearer " + token)
                    me.enqueue(object: Callback<DataProfile?> {
                        override fun onFailure(call: Call<DataProfile?>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<DataProfile?>, response: Response<DataProfile?>) {
                            Log.e("profile",response.body().toString())
                        }
                    })
                }
            })


        }
    }

}
