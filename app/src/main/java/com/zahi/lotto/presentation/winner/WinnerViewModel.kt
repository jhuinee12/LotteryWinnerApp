package com.zahi.lotto.presentation.winner

import androidx.lifecycle.MutableLiveData
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.base.BaseViewModel


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var drwNo: MutableLiveData<Long> = MutableLiveData<Long>()
    var lotteryData: MutableLiveData<LotteryNumber> = MutableLiveData<LotteryNumber>()

    fun getLottoWinnerNumber() {
        winnerRepository.subjectLotteryData.subscribe {
            lotteryData.value = it
        }
        winnerRepository.getLottoWinnerNumber(drwNo.value!!)
    }

}