package com.freddominant.eurozahl.usecases

import com.freddominant.eurozahl.util.DispatcherProvider
import com.freddominant.eurozahl.model.LottoResult
import com.freddominant.eurozahl.model.Result
import com.freddominant.eurozahl.repository.Repository
import com.freddominant.eurozahl.util.executeTask
import javax.inject.Inject

class GetLottoResultUseCase @Inject constructor (
    private val dispatcherProvider: DispatcherProvider,
    private val repository: Repository
): UseCase<Unit, List<LottoResult>> {
    override suspend operator fun invoke(arg: Unit): Result<List<LottoResult>> {
        return dispatcherProvider.io.executeTask {
            repository.getLotteries()
        }
    }
}