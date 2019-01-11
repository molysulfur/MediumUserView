package com.example.molys.mediumuserviewapplication.data

import android.os.Parcel
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.google.gson.annotations.SerializedName

data class DataPublication(
    @SerializedName("data") var data : MutableList<Publication>? = ArrayList()
) : KParcelable {
    constructor(parcel: Parcel) : this(
        data = mutableListOf<Publication>().apply {
            parcel.readTypedList(this,Publication.CREATOR)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::DataPublication)
    }
}