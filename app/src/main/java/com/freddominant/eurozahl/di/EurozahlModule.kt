package com.freddominant.eurozahl.di

import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(Singleton::class)
class EurozahlModule {

    @Singleton
    @Provides
    internal fun provideEurozahlApi(retrofit: Retrofit
    ): EurozahlApi = retrofit.create(EurozahlApi::class.java)

    @Singleton
    @Provides
    internal fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private companion object {
        const val BASE_URL = "https://www.lotto24.de/v1/"
    }

}