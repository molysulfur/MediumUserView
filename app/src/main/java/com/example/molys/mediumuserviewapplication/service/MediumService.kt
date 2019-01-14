package com.example.molys.mediumuserviewapplication.service

import com.example.molys.mediumuserviewapplication.data.AccessToken
import com.example.molys.mediumuserviewapplication.data.DataContributors
import com.example.molys.mediumuserviewapplication.data.DataProfile
import com.example.molys.mediumuserviewapplication.data.DataPublication
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MediumService {

    @Headers("Content-Type: application/x-www-form-urlencoded",
        "Accept: application/json",
        "Host: api.medium.com",
        "Accept-Charset: utf-8")
    @FormUrlEncoded
    @POST("tokens")
    fun getAccessToken(@Field("code") code : String,
                       @Field("client_id") clientId : String,
                       @Field("client_secret") clientSecret : String,
                       @Field("grant_type") grantType : String,
                       @Field("redirect_uri") redirectUri : String) : Observable<AccessToken>

    @GET("me")
    fun getProfile(@Header("Authorization") token: String) : Observable<DataProfile>

    @GET("users/{client_id}/publications")
    fun getPublicationsWithClientId(@Header("Authorization") token: String,@Path("client_id") clientId: String) : Observable<DataPublication>

    @GET("publications/{publication_id}/contributors")
        fun getPublicationWithId(@Header("Authorization") token: String,@Path("publication_id") publicationId: String?) : Observable<DataContributors>

    companion object {

        const val CLIENT_SECRET = "127218d26fd1c38485fd347c48289c3471933795"
        const val BASE_URL = "https://api.medium.com/v1/"
        const val GRANT_TYPE = "authorization_code"
        const val CLIENT_ID = "46e13914780c"
        const val REDIRECT_URI = "molysulfur://callback"

        fun getMediumService(): MediumService {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return  retrofit.create(MediumService::class.java)
        }
    }

}