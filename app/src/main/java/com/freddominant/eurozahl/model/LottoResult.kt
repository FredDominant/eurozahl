package com.freddominant.eurozahl.model

import com.google.gson.annotations.SerializedName

internal data class LottoResult(
    val lottery: String,
    val lastDraw: LastDraw,
    val nextDraw: NextDraw,
    val draws: Draw
)

internal abstract class Draw(
    open val drawIdentifier: String,
    open val lottery: String,
    open val drawDate: String,
    open val drawDateUTC: String,
)

internal data class Jackpot(
    val jackpotSupported: Boolean,
    val drawIdentifier: String,
    val lottery: String,
    val drawDate: String,
    val currency: String,
    val jackpots: Jackpots
)

internal data class Jackpots(
    @SerializedName("WC_1")
    val wc_One: String,
    @SerializedName("WC_2")
    val wc_Two: String,
)

internal data class DrawResult(
    val superNumber: Int,
    val numbers: List<Int>
)

internal data class LastDraw(
    override val drawIdentifier: String,
    override val lottery: String,
    override val drawDate: String,
    override val drawDateUTC: String,
    val timeZone: String,
    @SerializedName("cutofftime")
    val cutOffTime: String,
    val drawResult: DrawResult,
    val jackpots: Jackpots,
    val totalStake: String,
    val currency: String
): Draw(
    drawIdentifier = drawIdentifier,
    lottery = lottery,
    drawDate = drawDate,
    drawDateUTC = drawDateUTC,
)

internal data class NextDraw(
    override val drawIdentifier: String,
    override val lottery: String,
    override val drawDate: String,
    override val drawDateUTC: String,
    val cutOffTime: String,
    val timeZone: String,
    val drawResult: DrawResult,
    val jackpot: Jackpot,
): Draw(
    drawIdentifier = drawIdentifier,
    lottery = lottery,
    drawDate = drawDate,
    drawDateUTC = drawDateUTC,
)