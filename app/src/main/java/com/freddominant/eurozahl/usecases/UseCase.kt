package com.freddominant.eurozahl.usecases

import com.freddominant.eurozahl.model.Result

interface UseCase<I, O> {
    suspend operator fun invoke(arg: I) : Result<O>
}