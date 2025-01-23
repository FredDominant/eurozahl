package com.freddominant.eurozahl.domain.model

enum class Lottery(val lotteryName: String, val displayName: String) {
    LOTTO("6aus49", "LOTTO 6aus49"),
    EURO_JACKPOT("eurojackpot", "Eurojackpot"),
    SPIEL_77("spiel77", "Spiel 77"),
    SUPER_6("super6", "SUPER6");


    companion object {
        fun getLotteryFromName(lotteryName: String): Lottery {
            return entries.find { lotteryName == it.lotteryName } ?: throw IllegalArgumentException()
        }
    }
}