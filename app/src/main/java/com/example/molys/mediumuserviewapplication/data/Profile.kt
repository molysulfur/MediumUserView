package com.example.molys.mediumuserviewapplication.data

import android.os.Parcel
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.google.gson.annotations.SerializedName

@org.parceler.Parcel
data class Profile(
    @SerializedName("id") val id : String? ="",
    @SerializedName("username") val username : String? ="",
    @SerializedName("name") val name : String? ="",
    @SerializedName("url") val url : String? ="",
    @SerializedName("imageUrl") val imageUrl : String? =""
) : KParcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::Profile)
    }
}