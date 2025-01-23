package com.freddominant.eurozahl

import com.freddominant.eurozahl.data.mapper.MapperImpl
import com.freddominant.eurozahl.domain.model.DrawResult
import com.freddominant.eurozahl.domain.model.LastDraw
import com.freddominant.eurozahl.domain.model.Lottery
import com.freddominant.eurozahl.domain.model.LottoResult
import com.freddominant.eurozahl.domain.model.LottoResultUI
import com.freddominant.eurozahl.domain.model.NextDraw
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale

class MapperTest {

    val mapper = MapperImpl()

    @Test
    fun `map should correctly map lottoResult to lottoUIResult`() {
        val inputDate = "2025-01-22T18:25:00+01:00"
        val formattedDate = SimpleDateFormat("EE., dd.MM.yyyy", Locale.getDefault())
            .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault()).parse(inputDate)!!)

        val mainLottery = LottoResult(
            lottery = "6aus49",
            lastDraw = LastDraw(
                drawDate = inputDate,
                drawResult = DrawResult(
                    numbers = listOf(9, 31, 34, 35, 40, 41),
                    superNumber = 4,
                    number = null
                ),
                quotas = mapOf("WC_1" to "14698499.70"),
                lottery = "6aus49"
            ),
            nextDraw = NextDraw(drawDate = inputDate, lottery = "6aus49")
        )

        val sideLottery = LottoResult(
            lottery = "super6",
            lastDraw = LastDraw(
                drawDate = inputDate,
                drawResult = DrawResult(
                    number = "144989",
                    numbers = listOf(1, 2, 3, 4)
                ),
                quotas = emptyMap(),
                lottery = "super6"
            ),
            nextDraw = NextDraw(drawDate = inputDate, lottery = "super6")
        )

        val input = listOf(mainLottery, sideLottery)

        val expected = listOf(
            LottoResultUI(
                title = Lottery.LOTTO,
                date = formattedDate,
                nextDrawDate = formattedDate,
                winningNumbers = Pair(listOf(9, 31, 34, 35, 40, 41), listOf(4)),
                jackpotHeight = BigDecimal("14698499.70"),
                spiel77 = null,
                super6 = Pair(Lottery.SUPER_6.lotteryName, "144989")
            )
        )

        val result = mapper.map(input)
        assertEquals(expected, result)
    }

    @Test
    fun `parseDate should correctly format date`() {
        val inputDate = "2025-01-22T18:25:00+01:00"
        val expectedDate = SimpleDateFormat("EE., dd.MM.yyyy", Locale.getDefault())
            .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault()).parse(inputDate)!!)
        val result = mapper.parseDate(inputDate)
        assertEquals(expectedDate, result)
    }
}