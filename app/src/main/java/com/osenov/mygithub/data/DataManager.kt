package com.osenov.mygithub.data

import com.osenov.mygithub.CLIENT_ID
import com.osenov.mygithub.CLIENT_SECRET
import com.osenov.mygithub.data.model.AccessToken
import com.osenov.mygithub.data.network.LoginGithubClient
import com.osenov.mygithub.data.preference.PreferencesHelper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataManager @Inject constructor(
    private val githubLoginService: LoginGithubClient, prefHelper: PreferencesHelper) {

    internal val preferencesHelper = prefHelper

    fun getLoginToken(code: String): Single<AccessToken> {
        return githubLoginService.getAccessToken(CLIENT_ID, CLIENT_SECRET, code)
    }

}