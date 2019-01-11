package com.example.molys.mediumuserviewapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.molys.mediumuserviewapplication.data.AccessToken
import com.example.molys.mediumuserviewapplication.service.MediumService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val CLIENT_ID = "46e13914780c"
    private val CLIENT_SECRET = "127218d26fd1c38485fd347c48289c3471933795"
    private val REDIRECT_URI = "molysulfur://callback"
    private val BASE_URL = "https://api.medium.com/v1/"
    private val GRANT_TYPE = "authorization_code"
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }

    private fun init() {
        if (intent.data != null) {
            getAuthToken()
        } else {
            btnLogin.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data =
                        Uri.parse("https://medium.com/m/oauth/authorize?client_id=$CLIENT_ID&scope=basicProfile,publishPost&state=200&response_type=code&redirect_uri=$REDIRECT_URI")
                startActivity(intent)
            }
        }
    }

    private fun getAuthToken() {
        val uri = intent.data
        val code = uri!!.getQueryParameter("code")!!

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val accessTokenService = retrofit.create(MediumService::class.java)
            .getAccessToken(
                code, CLIENT_ID,
                CLIENT_SECRET,
                GRANT_TYPE,
                REDIRECT_URI
            )
        accessTokenService.enqueue(object : Callback<AccessToken?> {
            override fun onFailure(call: Call<AccessToken?>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<AccessToken?>, response: Response<AccessToken?>) {
                token = response.body()?.accessToken?: ""
                if (token != "") {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("token", token)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }

}
