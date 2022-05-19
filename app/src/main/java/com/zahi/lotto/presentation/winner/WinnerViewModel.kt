package com.zahi.lotto.presentation.winner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.base.BaseViewModel
import kotlinx.coroutines.launch


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var drwNo: MutableLiveData<Long> = MutableLiveData<Long>()
    var lotteryData: MutableLiveData<LotteryNumber> = MutableLiveData<LotteryNumber>()

    fun getLottoWinnerNumber() {
        winnerRepository.getLottoWinnerNumber(drwNo.value!!, this@WinnerViewModel)
    }

}