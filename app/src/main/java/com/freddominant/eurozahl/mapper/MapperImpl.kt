package com.freddominant.eurozahl.mapper

import com.freddominant.eurozahl.model.DrawResult
import com.freddominant.eurozahl.model.LastDraw
import com.freddominant.eurozahl.model.Lottery
import com.freddominant.eurozahl.model.LottoResult
import com.freddominant.eurozahl.model.LottoResultUI
import java.math.BigDecimal
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
                date = lottery.lastDraw.drawDate,
                nextDrawDate = lottery.nextDraw.drawDate,
                winningNumbers = lottery.lastDraw.drawResult.mapWinningNumbers(lotteryType),
                jackpotHeight = lottery.lastDraw.getJackpotHeight(),
                spiel77 = extractSideLotteryDetail(Lottery.SUPER_6, sideLotteries),
                super6 = extractSideLotteryDetail(Lottery.SPIEL_77, sideLotteries)
            )
        }
    }

    private fun LottoResult.isMainLottery(): Boolean {
        val lottery = Lottery.getLotteryFromName(lottery)
        return Lottery.LOTTO == lottery || Lottery.EURO_JACKPOT == lottery
    }

    private fun extractSideLotteryDetail(lotteryType: Lottery, sideLotteries: List<LottoResult>): Pair<String, String> {
        val lottery = sideLotteries.first { lotteryType.lotteryName == it.lottery }
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
}