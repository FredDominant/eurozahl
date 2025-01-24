package com.freddominant.eurozahl.domain.usecases

import com.freddominant.eurozahl.core.utils.CoroutineDispatcherProvider
import com.freddominant.eurozahl.core.utils.executeTask
import com.freddominant.eurozahl.data.mapper.MapperImpl
import com.freddominant.eurozahl.domain.LottoAccessor
import com.freddominant.eurozahl.domain.model.LottoResultUI
import com.freddominant.eurozahl.domain.model.Result
import com.freddominant.eurozahl.domain.repository.Repository
import javax.inject.Inject

class GetLottoResultUseCase @Inject constructor (
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val repository: Repository,
    private val mapper: MapperImpl
): UseCase<Unit, List<LottoResultUI>> {
    override suspend operator fun invoke(arg: Unit): Result<List<LottoResultUI>> {
        return dispatcherProvider.io.executeTask {
            val lottoUIResult = repository.getLotteries()
            val results = mapper.map(lottoUIResult)
            LottoAccessor.lottoResult = results
            results
        }
    }
}