package com.freddominant.eurozahl.usecases

import com.freddominant.eurozahl.mapper.MapperImpl
import com.freddominant.eurozahl.model.LottoResultUI
import com.freddominant.eurozahl.model.Result
import com.freddominant.eurozahl.repository.Repository
import com.freddominant.eurozahl.util.DispatcherProvider
import com.freddominant.eurozahl.util.executeTask
import javax.inject.Inject

class GetLottoResultUseCase @Inject constructor (
    private val dispatcherProvider: DispatcherProvider,
    private val repository: Repository,
    private val mapper: MapperImpl
): UseCase<Unit, List<LottoResultUI>> {
    override suspend operator fun invoke(arg: Unit): Result<List<LottoResultUI>> {
        return dispatcherProvider.io.executeTask {
            val lottoUIResult = repository.getLotteries()
            mapper.map(lottoUIResult)
        }
    }
}