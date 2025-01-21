package com.freddominant.eurozahl.di

import com.freddominant.eurozahl.util.CoroutineDispatcherProvider
import com.freddominant.eurozahl.util.DispatcherProvider
import com.freddominant.eurozahl.repository.Repository
import com.freddominant.eurozahl.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object EurozahlModule {

    @Singleton
    @Provides
    internal fun provideEurozahlApi(retrofit: Retrofit
    ): EurozahlApi = retrofit.create(EurozahlApi::class.java)

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private const val BASE_URL = "https://www.lotto24.de/v1/"
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    internal abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    internal abstract fun bindsDispatcher(coroutineDispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}