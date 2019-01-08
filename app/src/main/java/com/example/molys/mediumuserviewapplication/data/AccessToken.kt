package com.example.molys.mediumuserviewapplication.data

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("token_type") var tokenType : String,
    @SerializedName("access_token") var accessToken : String,
    @SerializedName("refresh_token") var refreshToken : String,
    @SerializedName("scope") var scope : Array<String>,
    @SerializedName("expires_at") var expiresAt : String

)