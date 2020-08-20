package com.osenov.mygithub.di.component

import android.content.Context
import com.osenov.mygithub.Application
import com.osenov.mygithub.data.DataManager
import com.osenov.mygithub.data.network.LoginGithubClient
import com.osenov.mygithub.data.preference.PreferencesHelper
import com.osenov.mygithub.di.module.ApplicationModule
import com.osenov.mygithub.di.module.LoginApiModule
import com.osenov.mygithub.di.scope.ApplicationContext
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LoginApiModule::class])
interface ApplicationComponent {

    @ApplicationContext fun context(): Context
    fun application(): Application
    fun githubLoginService(): LoginGithubClient
    fun dataManager(): DataManager
    fun preferencesHelper() : PreferencesHelper
}