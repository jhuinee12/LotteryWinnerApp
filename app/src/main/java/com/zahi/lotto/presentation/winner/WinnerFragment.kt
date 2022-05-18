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

        viewModel.drwNo.observe(this) {
            viewModel.getLottoWinnerNumber()
        }

        viewModel.lotteryData.observe(this) {
            binding.drwNo.text = it.drwNo.toString()
            binding.drwNoDate.text = it.drwNoDate
            binding.firstAccumamnt.text = it.firstAccumamnt.toString()
            binding.firstPrzwnerCo.text = it.firstPrzwnerCo.toString()
            binding.firstWinamnt.text = it.firstWinamnt.toString()
            binding.totSellamnt.text = it.totSellamnt.toString()
            binding.drwtNo1.text = it.drwtNo1.toString()
            binding.drwtNo2.text = it.drwtNo2.toString()
            binding.drwtNo3.text = it.drwtNo3.toString()
            binding.drwtNo4.text = it.drwtNo4.toString()
            binding.drwtNo5.text = it.drwtNo5.toString()
            binding.drwtNo6.text = it.drwtNo6.toString()
            binding.bnusNo.text = it.bnusNo.toString()
        }

        viewModel.drwNo.value = 1004
    }

    companion object {
        fun newInstance() = RecommendedFragment()
    }
}