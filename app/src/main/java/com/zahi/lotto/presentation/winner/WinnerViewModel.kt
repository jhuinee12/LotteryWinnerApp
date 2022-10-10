package com.zahi.lotto.presentation.winner

import androidx.lifecycle.MutableLiveData
import com.zahi.lotto.base.BaseViewModel
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.repositories.WinnerRepository


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var lotteryData: MutableLiveData<LotteryNumber> = MutableLiveData<LotteryNumber>()

    fun getLottoWinnerNumber(drwNo: Long) {
        winnerRepository.subjectLotteryData.subscribe {
            lotteryData.value = it
        }
        winnerRepository.getLottoWinnerNumber(drwNo)
    }
}