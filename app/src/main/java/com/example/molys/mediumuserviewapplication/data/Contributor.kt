package com.example.molys.mediumuserviewapplication.data

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("publicationId") val publicationId : String,
    @SerializedName("userId") val userId : String,
    @SerializedName("role") val role : String
)