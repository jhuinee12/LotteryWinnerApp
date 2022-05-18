package com.zahi.lotto.api

import com.zahi.lotto.entity.LotteryNumber
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface LottoApi {

    @GET("common.do")
    fun getLottoWinnerNumber(
        @Query("method") method: String = "getLottoNumber",
        @Query("drwNo") drwNo: Long,
    ): Observable<LotteryNumber>

}