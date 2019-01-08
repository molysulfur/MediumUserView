package com.example.molys.mediumuserviewapplication

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("id") val id : String,
    @SerializedName("username") val username : String,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String,
    @SerializedName("imageUrl") val imageUrl : String
)