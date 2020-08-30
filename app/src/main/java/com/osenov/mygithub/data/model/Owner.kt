package com.osenov.mygithub.data.model

import com.google.gson.annotations.SerializedName

data class Owner(@SerializedName("login")
val name: String,

@SerializedName("avatar_url")
val avatar: String)