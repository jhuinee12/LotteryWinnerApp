package com.zahi.lotto.repositories

import android.util.Log
import com.zahi.lotto.api.LottoApiService
import com.zahi.lotto.presentation.winner.WinnerViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class WinnerRepository {
    private val lottoApi = LottoApiService.api

    /**
     * public final Disposable subscribe(
     *      @NonNull Consumer<? super T> onNext,
     *      @NonNull Consumer<? super Throwable> onError,
     *      @NonNull Action onComplete)
     **/
    fun getLottoWinnerNumber(drwNo: Long, viewModel: WinnerViewModel) = lottoApi.getLottoWinnerNumber(drwNo = drwNo)
            .subscribeOn(Schedulers.io())   // 생산자(Observable) 쪽의 데이터 흐름을 제어 :  I/O 처리 작업 진행 시 사용
            .observeOn(AndroidSchedulers.mainThread())  // 소비자(Observer) 쪽에서 전달받은 데이터 처리 제어 : 메인 쓰레드에서 진행
            .subscribe({    // 데이터 발행 및 소비
                // onNext : 데이터의 발행을 알림
                Log.d("getLottoWinnerNumber-Repository", "onNext: $it")
                viewModel.lotteryData.value = it
            }, {
                // onError : 오류가 발생했음을 알림
                Log.d("getLottoWinnerNumber-Repository", "onError: $it")
            }, {
                // onComplete : 데이터 발행의 완료를 알림
                Log.d("getLottoWinnerNumber-Repository", "onComplete")
            })
}