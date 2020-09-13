package com.osenov.mygithub.data

import com.osenov.mygithub.CLIENT_ID
import com.osenov.mygithub.CLIENT_SECRET
import com.osenov.mygithub.data.model.AccessToken
import com.osenov.mygithub.data.model.Repository
import com.osenov.mygithub.data.model.RepositoryCommit
import com.osenov.mygithub.data.model.RepositoryMoreInfo
import com.osenov.mygithub.data.network.GithubClient
import com.osenov.mygithub.data.network.LoginGithubClient
import com.osenov.mygithub.data.preference.PreferencesHelper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject constructor(
    private val githubLoginService: LoginGithubClient,
    prefHelper: PreferencesHelper,
    private val githubClient: GithubClient) {

    internal val preferencesHelper = prefHelper

    fun getLoginToken(code: String): Single<AccessToken> {
        return githubLoginService.getAccessToken(CLIENT_ID, CLIENT_SECRET, code)
    }

    fun getRepositories(): Observable<ArrayList<Repository>> {
        return githubClient.getRepositories()
    }

    fun getRepositoryMoreInfo(owner : String, name : String): Observable<RepositoryMoreInfo> {
        return githubClient.getRepositoryMoreInfo(owner, name)
    }

    fun getCommits(owner : String, name : String): Observable<ArrayList<RepositoryCommit>> {
        return githubClient.getCommits(owner, name)
    }

}