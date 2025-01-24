package com.freddominant.eurozahl.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.freddominant.eurozahl.R
import com.freddominant.eurozahl.core.utils.formatBigDecimalToText
import com.freddominant.eurozahl.domain.model.Lottery
import com.freddominant.eurozahl.domain.model.LottoResultUI
import com.freddominant.eurozahl.ui.state.LotteryUiEvents
import java.math.BigDecimal

@Composable
internal fun LotteryResult(modifier: Modifier = Modifier, state: State<LotteryUiEvents>) {
    when (state.value) {
        is LotteryUiEvents.ShowProgress -> Loading(modifier = modifier)
        is LotteryUiEvents.Error -> Error(modifier = modifier)
        is LotteryUiEvents.ShowLotteries -> LotteryView(
            lotteries = (state.value as LotteryUiEvents.ShowLotteries).lotteries
        )
    }
}

@Composable
internal fun LotteryView(lotteries: List<LottoResultUI>) {
    LazyColumn {
        items(lotteries) { lottoResult ->
            println("lottoResult >> $lottoResult")
            LotteryCard(lottoResult)
        }
    }
}

@Composable
internal fun LotteryCard(lottery: LottoResultUI) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            LottoHeader(lottery)

            LottoDrawDate(lottery.date)

            lottery.jackpotHeight?.let { JackpotHeight(it) }

            LotteryBalls(numbers = lottery.winningNumbers)

            NextDrawSection(lottery.nextDrawDate)
        }
    }
}

@Composable
internal fun NextDrawSection(nextDrawDate: String) {
    Row {
        Text(
            text = "Next Draw on: ",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal)
        )
        LottoDrawDate(nextDrawDate)
    }
}

@Composable
internal fun LotteryBalls(
    modifier: Modifier = Modifier,
    numbers: Pair<List<Int>, List<Int>>
) {
    val (winningNumbers, specialNumbers) = numbers
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(winningNumbers + specialNumbers) { number ->
            val isSpecial = number in specialNumbers
            Ball(isSpecial = isSpecial, winningNumber = number)
        }
    }
}

@Composable
internal fun LottoHeader(lottery: LottoResultUI) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = lottery.title.displayName,
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        if (lottery.title == Lottery.LOTTO) SideLotteryResult(lottery)
    }
}
@Composable
internal fun LottoDrawDate(drawDate: String) {
    Text(
        text = drawDate,
        color = Color.Black,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
internal fun SideLotteryResult(lottery: LottoResultUI) {
    Column {
        if (lottery.spiel77 != null) {
            val (spiel77Text, number) = lottery.spiel77
            Text(
                text = "$spiel77Text: $number",
                color = Color(0xFFCC5F85),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        if (lottery.super6 != null) {
            val (super6Text, number) = lottery.super6
            Text(
                text = "$super6Text: $number",
                color = Color(0xFFCC5F85),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
internal fun Ball(isSpecial: Boolean, winningNumber: Int) {
    val cardColor = if (isSpecial) Color(0xFFFFC107) else Color(0xFF275881)
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(cardColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$winningNumber",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
internal fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(100.dp))
    }
}

@Composable
internal fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(20.dp),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "error image",
        )

        Text(
            text = "It's not you, it's us :)",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
internal fun JackpotHeight(jackpotHeight: BigDecimal) {
    Text(
        text = formatBigDecimalToText(jackpotHeight),
        color = Color.Black,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    )
}
