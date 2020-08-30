package com.osenov.mygithub.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.osenov.mygithub.ApiClient
import com.osenov.mygithub.BASE_URL
import com.osenov.mygithub.data.network.GithubClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Level
import javax.inject.Singleton


@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "token " + ApiClient.token)
                    .build()
                chain.proceed(newRequest)
            }.addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubService(okHttpClient: OkHttpClient, gson: Gson): GithubClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GithubClient::class.java)
    }
}