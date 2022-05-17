package com.zahi.lotto.presentation.winner

import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.presentation.recommended.RecommendedFragment
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.themovieapp.base.BaseFragment

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

    override fun initView() {

        (activity as MainActivity).changeToolbar(false)

        binding.apply { }
    }

    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)

        viewModel.drwNo.value = 1004

        viewModel.drwNo.observe(this) {
            viewModel.getLottoWinnerNumber()
        }
    }

    companion object {
        fun newInstance() = RecommendedFragment()
    }
}