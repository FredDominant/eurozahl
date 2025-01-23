package com.freddominant.eurozahl.domain.usecases

import com.freddominant.eurozahl.domain.model.Result

interface UseCase<I, O> {
    suspend operator fun invoke(arg: I) : Result<O>
}