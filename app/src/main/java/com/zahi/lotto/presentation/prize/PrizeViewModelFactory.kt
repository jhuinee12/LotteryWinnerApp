package com.zahi.lotto.presentation.prize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.repositories.WinnerRepository

class PrizeViewModelFactory(private val winnerRepository: WinnerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(WinnerRepository::class.java).newInstance(winnerRepository)
    }
}