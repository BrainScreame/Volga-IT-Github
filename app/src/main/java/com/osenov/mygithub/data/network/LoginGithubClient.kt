package com.osenov.mygithub.data.network

import com.osenov.mygithub.data.model.AccessToken
import com.osenov.mygithub.data.model.Repository
import io.reactivex.Single
import retrofit2.http.*


interface LoginGithubClient {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String
    ): Single<AccessToken>
}