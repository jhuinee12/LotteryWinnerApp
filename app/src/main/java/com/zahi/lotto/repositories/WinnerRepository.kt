package com.zahi.lotto.repositories

import com.zahi.lotto.api.LottoApiService

class WinnerRepository {
    private val lottoApi = LottoApiService.api

    fun getLottoWinnerNumber(drwNo: Int) = lottoApi.getLottoWinnerNumber(drwNo = drwNo)
}