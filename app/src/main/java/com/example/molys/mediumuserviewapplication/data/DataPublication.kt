package com.example.molys.mediumuserviewapplication.data

import com.google.gson.annotations.SerializedName

data class DataPublication(
    @SerializedName("data") val data : List<Publication>
)