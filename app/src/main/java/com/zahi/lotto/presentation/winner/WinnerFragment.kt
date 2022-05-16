package com.zahi.lotto.presentation.winner

import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.presentation.recommended.RecommendedFragment
import com.zahi.themovieapp.base.BaseFragment

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: RecommendedViewModel
    private lateinit var viewModelFactory: RecommendedViewModelFactory

    override fun initView() {

        (activity as MainActivity).changeToolbar(false)

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