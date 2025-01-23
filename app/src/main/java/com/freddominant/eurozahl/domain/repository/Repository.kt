package com.freddominant.eurozahl.domain.repository

import com.freddominant.eurozahl.domain.model.LottoResult

interface Repository {
    suspend fun getLotteries(): List<LottoResult>
}