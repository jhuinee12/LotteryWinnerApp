package com.zahi.lotto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LottoApiService {
    var api: LottoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.dhlottery.co.kr/common/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(LottoApi::class.java)
    }
}