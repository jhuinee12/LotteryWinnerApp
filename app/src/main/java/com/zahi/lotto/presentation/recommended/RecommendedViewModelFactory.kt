package com.zahi.lotto.presentation.recommended

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RecommendedViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}