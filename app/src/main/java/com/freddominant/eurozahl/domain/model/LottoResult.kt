package com.freddominant.eurozahl.domain.model

data class LottoResult(
    val lottery: String,
    val lastDraw: LastDraw,
    val nextDraw: NextDraw
)

data class LastDraw(
    val lottery: String,
    val drawDate: String,
    val drawResult: DrawResult,
    val quotas: Map<String, String>
)

data class NextDraw(
    val lottery: String,
    val drawDate: String
)

data class DrawResult(
    val superNumber: Int? = null,
    val numbers: List<Int>,
    val number: String?,
    val euroNumbers: List<Int>? = null
)
