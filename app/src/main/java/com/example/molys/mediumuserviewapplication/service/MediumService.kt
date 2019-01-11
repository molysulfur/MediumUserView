package com.example.molys.mediumuserviewapplication.service

import com.example.molys.mediumuserviewapplication.data.*
import retrofit2.Call
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
                       @Field("redirect_uri") redirectUri : String) : Call<AccessToken>

    @GET("me")
    fun getProfile(@Header("Authorization") token: String) : Call<DataProfile>

    @GET("users/{client_id}/publications")
    fun getPublicationsWithClientId(@Header("Authorization") token: String,@Path("client_id") clientId: String) : Call<DataPublication>

    @GET("publications/{publication_id}/contributors")
        fun getPublicationWithId(@Header("Authorization") token: String,@Path("publication_id") publicationId: String?) : Call<DataContributors>

}