package com.freddominant.eurozahl.model

import com.google.gson.annotations.SerializedName

data class LottoResult(
    val lottery: String,
    val lastDraw: LastDraw,
    val nextDraw: NextDraw,
    val draws: List<Any>
)

abstract class Draw(
    open val drawIdentifier: String,
    open val lottery: String,
    open val drawDate: String,
    open val drawDateUTC: String,
)

data class Jackpot(
    val drawIdentifier: String,
    val lottery: String,
    val drawDate: String,
    val currency: String,
    val jackpots: Map<String, String>,
    val jackpotSupported: Boolean,
)

data class DrawResult(
    val superNumber: Int? = null,
    val numbers: List<Int>,
    val number: String?,
    val euroNumbers: List<Int>? = null
)

data class LastDraw(
    override val drawIdentifier: String,
    override val lottery: String,
    override val drawDate: String,
    override val drawDateUTC: String,
    val drawResult: DrawResult,
    val quotas: Map<String, String>,
    val nonMonetaryQuotas: Map<Any, Any>?,
    val winners: Map<String, Double>,
    val totalStake: String,
    val currency: String
): Draw(
    drawIdentifier = drawIdentifier,
    lottery = lottery,
    drawDate = drawDate,
    drawDateUTC = drawDateUTC,
)

data class NextDraw(
    override val drawIdentifier: String,
    override val lottery: String,
    override val drawDate: String,
    override val drawDateUTC: String,
    @SerializedName("cutofftime")
    val cutOffTime: String,
    val timeZone: String,
    val jackpot: Jackpot,
): Draw(
    drawIdentifier = drawIdentifier,
    lottery = lottery,
    drawDate = drawDate,
    drawDateUTC = drawDateUTC,
)