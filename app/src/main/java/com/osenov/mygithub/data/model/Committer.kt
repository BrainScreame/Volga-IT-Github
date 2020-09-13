package com.osenov.mygithub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Committer(
    @SerializedName("name")
    val name: String,

    @SerializedName("date")
    val date: Date
)