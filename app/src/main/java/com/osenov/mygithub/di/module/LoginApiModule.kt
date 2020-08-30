package com.osenov.mygithub.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.osenov.mygithub.LOGIN_URL
import com.osenov.mygithub.data.network.LoginGithubClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class LoginApiModule {

    @Provides
    @Singleton
    fun provideLoginGithubService(): LoginGithubClient {
        return Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LoginGithubClient::class.java)
    }
}
