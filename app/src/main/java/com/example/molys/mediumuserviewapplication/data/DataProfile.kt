package com.example.molys.mediumuserviewapplication.data

import android.os.Build
import android.os.Parcel
import android.support.annotation.RequiresApi
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.example.molys.mediumuserviewapplication.readTypedObjectCompat
import com.google.gson.annotations.SerializedName

@Suppress("UNREACHABLE_CODE")
data class DataProfile(
    @SerializedName("data") val data : Profile?
) : KParcelable {
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(parcel: Parcel) : this(
        parcel.readTypedObjectCompat(Profile.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::DataProfile)
    }
}