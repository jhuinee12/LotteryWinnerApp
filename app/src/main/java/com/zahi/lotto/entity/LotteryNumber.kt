package com.zahi.lotto.entity

data class LotteryNumber(
    val drwNo: Int,             // 회차번호
    val drwNoDate: String,      // 추첨일
    val firstAccumamnt: Int,    // 총 1등 당첨금
    val firstPrzwnerCo: Int,    // 1등 당첨 인원
    val firstWinamnt: Int,      // 1등 수령액
    val totSellamnt: Int,       // 총 판매 금액
    val drwtNo1: Int,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo4: Int,
    val drwtNo5: Int,
    val drwtNo6: Int,
    val bnusNo: Int
)
