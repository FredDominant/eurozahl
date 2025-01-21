package com.freddominant.eurozahl.repository

import com.freddominant.eurozahl.di.EurozahlApi
import com.freddominant.eurozahl.model.LottoResult
import javax.inject.Inject

 internal class RepositoryImpl @Inject constructor(
    private val eurozahlApi: EurozahlApi
): Repository {
    override suspend fun getLotteries(): List<LottoResult> {
        return eurozahlApi.getResults()
    }
}