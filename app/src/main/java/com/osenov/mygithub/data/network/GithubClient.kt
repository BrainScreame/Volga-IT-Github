package com.osenov.mygithub.data.network

import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryMoreInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubClient {
    @GET("repositories")
    fun getRepositories(): Observable<ArrayList<Repository>>

    @GET("repos/{owner}/{name}")
    fun getRepositoryMoreInfo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): Observable<RepositoryMoreInfo>

}