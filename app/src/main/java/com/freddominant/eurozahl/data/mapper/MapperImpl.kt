package com.freddominant.eurozahl.data.mapper

import androidx.annotation.VisibleForTesting
import com.freddominant.eurozahl.domain.model.DrawResult
import com.freddominant.eurozahl.domain.model.LastDraw
import com.freddominant.eurozahl.domain.model.Lottery
import com.freddominant.eurozahl.domain.model.LottoResult
import com.freddominant.eurozahl.domain.model.LottoResultUI
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

typealias lottoResult = List<LottoResult>
typealias lottoUIResult = List<LottoResultUI>

class MapperImpl @Inject constructor() : Mapper<lottoResult, lottoUIResult> {
    override fun map(arg: lottoResult): lottoUIResult {
        val (mainLotteries, sideLotteries) = arg.partition { it.isMainLottery() }
        return mainLotteries.map { lottery ->
            val lotteryType = Lottery.getLotteryFromName(lottery.lottery)
            LottoResultUI(
                title = lotteryType,
                date = parseDate(lottery.lastDraw.drawDate),
                nextDrawDate = parseDate(lottery.nextDraw.drawDate),
                winningNumbers = lottery.lastDraw.drawResult.mapWinningNumbers(lotteryType),
                jackpotHeight = lottery.lastDraw.getJackpotHeight(),
                spiel77 = extractSideLotteryDetail(Lottery.SPIEL_77, sideLotteries),
                super6 = extractSideLotteryDetail(Lottery.SUPER_6, sideLotteries)
            )
        }
    }

    @VisibleForTesting
    fun parseDate(date: String): String {
        val inputFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val outputFormat = SimpleDateFormat(SIMPLIFIED_FORMAT, Locale.getDefault())
        val formattedDate = inputFormat.parse(date) ?: return ""
        return outputFormat.format(formattedDate)
    }

    private fun LottoResult.isMainLottery(): Boolean {
        val lottery = Lottery.getLotteryFromName(lottery)
        return Lottery.LOTTO == lottery || Lottery.EURO_JACKPOT == lottery
    }

    private fun extractSideLotteryDetail(lotteryType: Lottery, sideLotteries: List<LottoResult>): Pair<String, String>? {
        val lottery = sideLotteries.firstOrNull{ lotteryType.lotteryName == it.lottery } ?: return null
        return Pair(lotteryType.lotteryName, lottery.lastDraw.drawResult.number.orEmpty())
    }

    private fun LastDraw.getJackpotHeight(): BigDecimal {
        return quotas.values.map { BigDecimal(it) }.first()
    }

    private fun DrawResult.mapWinningNumbers(lotteryType: Lottery): Pair<List<Int>, List<Int>> {
        return when (lotteryType) {
            Lottery.LOTTO -> Pair(numbers, listOf(requireNotNull(superNumber)))
            Lottery.EURO_JACKPOT -> Pair(numbers, requireNotNull(euroNumbers))
            Lottery.SUPER_6, Lottery.SPIEL_77 -> Pair(numbers, emptyList())
        }
    }

    private companion object {
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX"
        const val SIMPLIFIED_FORMAT = "EE., dd.MM.yyyy"
    }
}