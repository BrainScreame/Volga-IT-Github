package com.osenov.mygithub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Commit(
    @SerializedName("committer")
    val committer: Committer,

    @SerializedName("message")
    val message: String
)