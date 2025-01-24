package com.freddominant.eurozahl.ui.state

import com.freddominant.eurozahl.domain.model.LottoResultUI

sealed interface LotteryUiEvents {
    data object ShowProgress : LotteryUiEvents
    data object Error: LotteryUiEvents
    data class ShowLotteries(val lotteries: List<LottoResultUI>): LotteryUiEvents
}