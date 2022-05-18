package com.zahi.lotto.repositories

import android.util.Log
import com.zahi.lotto.api.LottoApiService
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.presentation.winner.WinnerViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WinnerRepository {
    private val lottoApi = LottoApiService.api

//    fun getLottoWinnerNumber(drwNo: Int) = lottoApi.getLottoWinnerNumber(drwNo = drwNo)

    fun getLottoWinnerNumber(drwNo: Int, viewModel: WinnerViewModel) = lottoApi.getLottoWinnerNumber(drwNo = drwNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // onNext
                Log.d("getLottoWinnerNumber-Repository", "onNext: $it")
                viewModel.lotteryData.value = it
            }, {
                // onError
                Log.d("getLottoWinnerNumber-Repository", "onError: $it")
            }, {
                // onComplete
                Log.d("getLottoWinnerNumber-Repository", "onComplete")
            })

    fun lottoWinnerNumber(drwNo: Int) = lottoApi.getLottoWinnerNumber(drwNo = drwNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}