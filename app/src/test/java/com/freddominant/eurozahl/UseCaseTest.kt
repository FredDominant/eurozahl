package com.freddominant.eurozahl

import com.freddominant.eurozahl.data.mapper.MapperImpl
import com.freddominant.eurozahl.domain.model.LottoResult
import com.freddominant.eurozahl.domain.model.Result
import com.freddominant.eurozahl.domain.repository.Repository
import com.freddominant.eurozahl.domain.usecases.GetLottoResultUseCase
import com.freddominant.eurozahl.utils.CoroutineTestRule
import com.freddominant.eurozahl.utils.TestCoroutineDispatcherProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val repository: Repository = mockk()
    private val mapperImpl: MapperImpl = mockk(relaxed = true)
    private val dispatcherProvider = TestCoroutineDispatcherProvider()

    @Test
    fun `should map result if network call is successful`() = runTest {
        val fakeLotto: LottoResult = mockk(relaxed = true)
        val fakeResult = listOf(fakeLotto)
        val useCase = GetLottoResultUseCase(dispatcherProvider, repository, mapperImpl)
        coEvery { repository.getLotteries() } returns fakeResult
        val result = useCase.invoke(Unit)
        coVerify { repository.getLotteries() }
        verify { mapperImpl.map(any()) }
        assertTrue(result is Result.Success)
    }

    @Test
    fun `should handle error correctly`() = runTest {
        val fakeResult = IOException()
        val useCase = GetLottoResultUseCase(dispatcherProvider, repository, mapperImpl)
        coEvery { repository.getLotteries() } throws fakeResult
        val result = useCase.invoke(Unit)
        coVerify { repository.getLotteries() }
        assertTrue(result is Result.Error)
    }

}