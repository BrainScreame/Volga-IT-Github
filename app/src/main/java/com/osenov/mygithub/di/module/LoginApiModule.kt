package com.osenov.mygithub.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.osenov.mygithub.LOGIN_URL
import com.osenov.mygithub.data.network.LoginGithubClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class LoginApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    @Singleton
    fun provideLoginGithubService(okHttpClient: OkHttpClient, gson: Gson): LoginGithubClient {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(LoginGithubClient::class.java)
    }
}
