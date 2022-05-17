package com.zahi.lotto.entity

import com.google.gson.annotations.SerializedName

data class LotteryNumber(
     @SerializedName("drwNo")           val drwNo: Long,               // 회차번호
     @SerializedName("drwNoDate")       val drwNoDate: String,        // 추첨일
     @SerializedName("firstAccumamnt")  val firstAccumamnt: Long,      // 총 1등 당첨금
     @SerializedName("firstPrzwnerCo")  val firstPrzwnerCo: Long,      // 1등 당첨 인원
     @SerializedName("firstWinamnt")    val firstWinamnt: Long,        // 1등 수령액
     @SerializedName("totSellamnt")     val totSellamnt: Long,         // 총 판매 금액
     @SerializedName("drwtNo1")         val drwtNo1: Int,
     @SerializedName("drwtNo2")         val drwtNo2: Int,
     @SerializedName("drwtNo3")         val drwtNo3: Int,
     @SerializedName("drwtNo4")         val drwtNo4: Int,
     @SerializedName("drwtNo5")         val drwtNo5: Int,
     @SerializedName("drwtNo6")         val drwtNo6: Int,
     @SerializedName("bnusNo")          val bnusNo: Int
)
