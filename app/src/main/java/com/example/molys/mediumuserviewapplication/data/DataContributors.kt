package com.example.molys.mediumuserviewapplication.data

import android.os.Parcel
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.google.gson.annotations.SerializedName

data class DataContributors(
    @SerializedName("data") val data : MutableList<Contributor>?
) : KParcelable {
    constructor(parcel: Parcel) : this(
        data = mutableListOf<Contributor>().apply {
            parcel.readTypedList(this,Contributor.CREATOR)
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::DataContributors)
    }
}