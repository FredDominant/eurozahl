package com.freddominant.eurozahl

import com.freddominant.eurozahl.domain.model.LottoResultUI
import com.freddominant.eurozahl.domain.model.Result
import com.freddominant.eurozahl.domain.usecases.GetLottoResultUseCase
import com.freddominant.eurozahl.ui.state.LotteryUiEvents
import com.freddominant.eurozahl.ui.viewmodel.EurozahlViewModel
import com.freddominant.eurozahl.utils.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private val lotteryEvents = mutableListOf<LotteryUiEvents>()
    private val useCase: GetLottoResultUseCase = mockk(relaxed = true)
    private val viewModel = EurozahlViewModel(useCase)

    @Before
    fun clear() {
        lotteryEvents.clear()
    }

    @Test
    fun `getLotteryResult should show loading before fetching data`() = runTest {
       coEvery { useCase.invoke(Unit) } returns mockk(relaxed = true)
        val job = launch(testDispatcher) {
            viewModel.lotteryEvents.toList(lotteryEvents)
        }
        viewModel.getLotteryResult()
        val firstEvent = lotteryEvents.first()
        assertTrue(firstEvent is LotteryUiEvents.ShowProgress)
        job.cancel()
    }

    @Test
    fun `getLotteryResult should handle successful data fetch`() = runTest {
        val fakeResult: List<LottoResultUI> = mockk(relaxed = true)
        coEvery { useCase.invoke(Unit) } returns Result.Success(fakeResult)

        val job = launch(testDispatcher) {
            viewModel.lotteryEvents.toList(lotteryEvents)
        }

        viewModel.getLotteryResult()
        val lastEvent = lotteryEvents.last()
        assertTrue(lastEvent is LotteryUiEvents.ShowLotteries)
        coVerify { useCase.invoke(Unit) }
        job.cancel()
    }

    @Test
    fun `getLotteryResult should handle error correctly`() = runTest {
        val fakeResult = IOException()
        coEvery { useCase.invoke(Unit) } returns  Result.Error(fakeResult)

        val job = launch(testDispatcher) {
            viewModel.lotteryEvents.toList(lotteryEvents)
        }

        viewModel.getLotteryResult()
        val lastEvent = lotteryEvents.last()
        assertTrue(lastEvent is LotteryUiEvents.Error)
        coVerify { useCase.invoke(Unit) }
        job.cancel()
    }
}