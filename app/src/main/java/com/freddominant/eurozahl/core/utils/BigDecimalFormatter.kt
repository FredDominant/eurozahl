package com.freddominant.eurozahl.core.utils

import java.math.BigDecimal
import java.text.DecimalFormat

fun formatBigDecimalToText(value: BigDecimal): String {
    val thousand = BigDecimal(1_000)
    val million = BigDecimal(1_000_000)
    val billion = BigDecimal(1_000_000_000)

    return when {
        value >= billion -> {
            val billions = value.divide(billion)
            "${formatDecimal(billions)} billion"
        }
        value >= million -> {
            val millions = value.divide(million)
            "${formatDecimal(millions)} million"
        }
        value >= thousand -> {
            val thousands = value.divide(thousand)
            "${formatDecimal(thousands)} thousand"
        }
        else -> value.toPlainString()
    }
}

private fun formatDecimal(value: BigDecimal): String {
    val decimalFormat = DecimalFormat("#.#")
    return decimalFormat.format(value)
}