package com.osenov.mygithub.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryMoreInfo(
    @SerializedName("language")
    val language: String?,

    @SerializedName("stargazers_count")
    val stars: String?,

    @SerializedName("forks")
    val forks: String?
) : Parcelable