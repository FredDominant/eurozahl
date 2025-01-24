package com.freddominant.eurozahl.core.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.freddominant.eurozahl.core.utils.formatBigDecimalToText
import com.freddominant.eurozahl.domain.LottoAccessor
import com.freddominant.eurozahl.domain.model.Lottery
import com.freddominant.eurozahl.domain.model.LottoResultUI
import java.math.BigDecimal

class EurozahlWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            LotteryView(LottoAccessor.lottoResult)
        }
    }

    @Composable
    fun LotteryView(lotteries: List<LottoResultUI>) {
        Column(
            modifier = GlanceModifier.background(Color.White)
        ) {
            lotteries.map {
                LotteryCard(it)
            }
        }
    }

    @Composable
    fun LotteryCard(lottery: LottoResultUI) {
        Column(
            modifier = GlanceModifier
                .padding(16.dp)
                .clickable(
                    actionRunCallback<OpenLotto24Action>(
                        parameters = actionParametersOf(OpenLotto24Action.OpenUrlKey to OpenLotto24Action.URL)
                    )
                )
        ) {
            LottoHeader(lottery)

            LottoDrawDate(lottery.date)

            lottery.jackpotHeight?.let { JackpotHeight(it) }

            LotteryBalls(numbers = lottery.winningNumbers)

            NextDrawSection(lottery.nextDrawDate)
        }
    }

    @Composable
    fun NextDrawSection(nextDrawDate: String) {
        Row {
            Text(text = "Next Draw on: ")
            LottoDrawDate(nextDrawDate)
        }
    }

    @Composable
    fun LotteryBalls(
        modifier: GlanceModifier = GlanceModifier,
        numbers: Pair<List<Int>, List<Int>>
    ) {
        val (winningNumbers, specialNumbers) = numbers
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            (winningNumbers + specialNumbers).map {
                val isSpecial = it in specialNumbers
                Ball(isSpecial = isSpecial, winningNumber = it)
            }
        }
    }

    @Composable
    fun LottoHeader(lottery: LottoResultUI) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
        ) {
            Text(
                modifier = GlanceModifier.defaultWeight(),
                text = lottery.title.displayName,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            if (lottery.title == Lottery.LOTTO) SideLotteryResult(lottery)
        }
    }

    @Composable
    fun LottoDrawDate(drawDate: String) {
        Text(text = drawDate)
    }

    @Composable
    fun SideLotteryResult(lottery: LottoResultUI) {
        Column {
            if (lottery.spiel77 != null) {
                val (spiel77Text, number) = lottery.spiel77
                Text(text = "$spiel77Text: $number")
            }

            if (lottery.super6 != null) {
                val (super6Text, number) = lottery.super6
                Text(text = "$super6Text: $number")
            }
        }
    }

    @Composable
    fun Ball(isSpecial: Boolean, winningNumber: Int) {
        val cardColor = if (isSpecial) Color(0xFFFFC107) else Color(0xFF275881)
        Box(
            modifier = GlanceModifier
                .size(40.dp)
                .padding(horizontal = 8.dp)
                .cornerRadius(50.dp)
                .background(cardColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$winningNumber",
                style = TextStyle(color = GlanceTheme.colors.onBackground)
            )
        }
    }

    @Composable
    fun JackpotHeight(jackpotHeight: BigDecimal) {
        Text(text = formatBigDecimalToText(jackpotHeight))
    }

    private fun handleClick() {

    }
}
