package com.example.molys.mediumuserviewapplication.data

import android.os.Parcel
import com.example.molys.mediumuserviewapplication.KParcelable
import com.example.molys.mediumuserviewapplication.parcelableCreator
import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("token_type") var tokenType : String? ="",
    @SerializedName("access_token") var accessToken : String? = "",
    @SerializedName("refresh_token") var refreshToken : String? = "",
    @SerializedName("scope") var scope : List<String>?,
    @SerializedName("expires_at") var expiresAt : String? = ""

) : KParcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tokenType)
        parcel.writeString(accessToken)
        parcel.writeString(refreshToken)
        parcel.writeStringList(scope)
        parcel.writeString(expiresAt)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::AccessToken)
    }
}
