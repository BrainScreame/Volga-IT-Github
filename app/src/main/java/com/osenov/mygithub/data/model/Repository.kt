package com.osenov.mygithub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("description")
    val description: String?,

    var repositoryMoreInfo: RepositoryMoreInfo?
) : Parcelable