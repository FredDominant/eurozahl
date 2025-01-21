package com.freddominant.eurozahl.repository

import com.freddominant.eurozahl.model.LottoResult

interface Repository {
    suspend fun getLotteries(): List<LottoResult>
}