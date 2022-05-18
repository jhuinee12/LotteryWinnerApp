package com.zahi.lotto.presentation.winner

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.presentation.recommended.RecommendedFragment
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.themovieapp.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

    override fun initView() {

        (activity as MainActivity).changeToolbar(false)

        Log.d("TAG", "initView: latestDrwNo - ${latestDrwNo()}")

        binding.apply {
            dataViewModel = viewModel
        }

        viewModel.drwNo.value = latestDrwNo()
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)

        viewModel.drwNo.observe(this) {
            viewModel.getLottoWinnerNumber()
        }
    }

    /* 로또의 가장 최신 회차 번호 구하기 */
    @SuppressLint("SimpleDateFormat")
    fun latestDrwNo(): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//        val nowDate = dateFormat.parse("2022-05-14 19:59:59")
        val startDate = dateFormat.parse("2002-12-07 20:00:00")
        val diff = Date().time - startDate.time
        return (diff / (86400 * 1000 * 7))+1
    }

    companion object {
        fun newInstance() = RecommendedFragment()
    }
}