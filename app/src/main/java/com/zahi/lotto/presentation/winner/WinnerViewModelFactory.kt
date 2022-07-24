package com.zahi.lotto.presentation.winner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WinnerViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}