package com.zahi.lotto.presentation.home

import android.view.View
import androidx.navigation.fragment.findNavController
import com.zahi.lotto.R
import com.zahi.lotto.base.BaseFragment
import com.zahi.lotto.databinding.FragmentHomeBinding
import com.zahi.lotto.presentation.MainActivity


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun initView() {

        activity.changeToolbar(isVisible = false)

        binding.apply {
            fragment = this@HomeFragment
        }
    }

    fun goPrize(v: View) {
        findNavController().navigate(R.id.actionPrizeFragment)
    }

    fun goWinner(v: View) {
        findNavController().navigate(R.id.actionWinnerFragment)
    }

    fun goRecommended(v: View) {
        findNavController().navigate(R.id.actionRecommendedFragment)
    }



    companion object {
        fun newInstance() = HomeFragment().apply {  }
    }
}