package com.freddominant.eurozahl.di

import com.freddominant.eurozahl.model.LottoResult
import retrofit2.http.GET

internal interface EurozahlApi {
    @GET(GET_RESULTS_URL)
    suspend fun getResults(): List<LottoResult>

    private companion object {
        const val GET_RESULTS_URL = "drawinfo/aggregated/6aus49,eurojackpot,super6,spiel77"
    }
}