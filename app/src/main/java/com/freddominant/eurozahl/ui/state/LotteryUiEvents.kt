package com.freddominant.eurozahl.ui.state

import com.freddominant.eurozahl.domain.model.LottoResultUI

sealed interface LotteryUiEvents {
    data class ShowProgress(val showProgress: Boolean): LotteryUiEvents
    data class Error(val errorMessage: String): LotteryUiEvents
    data class ShowLotteries(val lotteries: List<LottoResultUI>): LotteryUiEvents
}