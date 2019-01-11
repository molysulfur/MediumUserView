package com.example.molys.mediumuserviewapplication.data

import android.os.Parcel
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("publicationId") val publicationId : String? = "",
    @SerializedName("userId") val userId : String? = "",
    @SerializedName("role") val role : String? = ""
) : KParcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(publicationId)
        parcel.writeString(userId)
        parcel.writeString(role)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Contributor)
    }
}