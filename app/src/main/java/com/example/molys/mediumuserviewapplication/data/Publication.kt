package com.example.molys.mediumuserviewapplication.data

import com.google.gson.annotations.SerializedName

data class Publication(
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("url") val url : String,
    @SerializedName("imageUrl") val imageUrl : String
)