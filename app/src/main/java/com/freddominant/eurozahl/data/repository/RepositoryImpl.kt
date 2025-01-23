package com.freddominant.eurozahl.data.repository

import com.freddominant.eurozahl.core.di.EurozahlApi
import com.freddominant.eurozahl.domain.repository.Repository
import com.freddominant.eurozahl.domain.model.LottoResult
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val eurozahlApi: EurozahlApi
): Repository {
    override suspend fun getLotteries(): List<LottoResult> {
        return eurozahlApi.getResults()
    }
}