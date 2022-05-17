package com.zahi.lotto.presentation.winner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.themovieapp.base.BaseViewModel
import kotlinx.coroutines.launch


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var drwNo: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getLottoWinnerNumber() {
        viewModelScope.launch {
            winnerRepository.getLottoWinnerNumber(drwNo.value!!)
        }
    }

}