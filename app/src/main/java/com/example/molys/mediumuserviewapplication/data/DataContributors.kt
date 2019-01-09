package com.example.molys.mediumuserviewapplication.data

import com.google.gson.annotations.SerializedName

data class DataContributors(
    @SerializedName("data") val data : List<Contributor>
)