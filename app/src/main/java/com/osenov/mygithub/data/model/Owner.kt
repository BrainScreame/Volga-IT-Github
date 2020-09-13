package com.osenov.mygithub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @SerializedName("login")
    val name: String,

    @SerializedName("avatar_url")
    val avatar: String
) : Parcelable