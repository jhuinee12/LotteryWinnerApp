package com.zahi.lotto.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object LottoApiService {
    var api: LottoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.dhlottery.co.kr/")
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())   // 받은 응답을 observable 형태로 변환
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(LottoApi::class.java)
    }
}