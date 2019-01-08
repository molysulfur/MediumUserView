package com.example.molys.mediumuserviewapplication

import retrofit2.Call
import retrofit2.http.*

interface OauthService {

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
                       @Field("redirect_uri") redirectUri : String) : Call<AccessToken>

    @GET("me")
    fun getMe(@Header("Authorization") token: String) : Call<DataProfile>

}