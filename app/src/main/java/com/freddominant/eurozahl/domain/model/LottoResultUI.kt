package com.freddominant.eurozahl.domain.model

import java.math.BigDecimal

data class LottoResultUI (
    val title: Lottery,
    val date: String,
    val nextDrawDate: String,
    val winningNumbers: Pair<List<Int>, List<Int>>,
    val jackpotHeight: BigDecimal?,
    val spiel77: Pair<String, String>?,
    val super6: Pair<String, String>?
)