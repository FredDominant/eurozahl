package com.freddominant.eurozahl.core.di

import com.freddominant.eurozahl.data.repository.RepositoryImpl
import com.freddominant.eurozahl.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}