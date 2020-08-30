package com.osenov.mygithub.data.model

import com.google.gson.annotations.SerializedName

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
)