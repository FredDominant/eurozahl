package com.freddominant.eurozahl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddominant.eurozahl.domain.model.LottoResultUI
import com.freddominant.eurozahl.domain.model.Result
import com.freddominant.eurozahl.domain.usecases.GetLottoResultUseCase
import com.freddominant.eurozahl.ui.state.LotteryUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EurozahlViewModel @Inject constructor (
    private val useCase: GetLottoResultUseCase
): ViewModel() {

    private val _lotteryEvents = MutableSharedFlow<LotteryUiEvents>()
    val lotteryEvents = _lotteryEvents.asSharedFlow()

    internal fun getLotteryResult() {
        viewModelScope.launch {
            updateState(LotteryUiEvents.ShowProgress)
            when(val result = useCase.invoke(Unit)) {
                is Result.Success -> handleSuccess(result.data)
                is Result.Error -> handleError()
            }
        }
    }

    private suspend fun handleSuccess(response: List<LottoResultUI>) {
        val newState = LotteryUiEvents.ShowLotteries(response)
        updateState(newState)
    }

    private suspend fun handleError() {
        val newState = LotteryUiEvents.Error
        updateState(newState)
    }

    private suspend fun updateState(newState: LotteryUiEvents) {
        _lotteryEvents.emit(newState)
    }
}