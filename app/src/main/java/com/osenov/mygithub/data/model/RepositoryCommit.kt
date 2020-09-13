package com.osenov.mygithub.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryCommit(
    @SerializedName("commit")
    val commit: Commit,

    @SerializedName("author")
    val author: Owner?
)