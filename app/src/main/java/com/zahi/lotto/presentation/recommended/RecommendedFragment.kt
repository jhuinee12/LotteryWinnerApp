package com.zahi.lotto.presentation.recommended

import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentRecommendedBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.base.BaseFragment

class RecommendedFragment : BaseFragment<FragmentRecommendedBinding>(R.layout.fragment_recommended) {

    private lateinit var viewModel: RecommendedViewModel
    private lateinit var viewModelFactory: RecommendedViewModelFactory

    override fun initView() {


        binding.apply { }
    }

    override fun initViewModel() {
        viewModelFactory = RecommendedViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecommendedViewModel::class.java)
    }

    companion object {
        fun newInstance() = RecommendedFragment().apply { }
    }
}