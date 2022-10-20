package com.zahi.lotto.presentation.prize

import android.annotation.SuppressLint
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zahi.lotto.R
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.base.BaseFragment
import com.zahi.lotto.databinding.FragmentPrizeBinding
import com.zahi.lotto.util.findLatestDrwNo.latestDrwNo
import java.util.*

/**
 * 당첨 결과 조회 화면
 */
class PrizeFragment : BaseFragment<FragmentPrizeBinding>(R.layout.fragment_prize) {

    private lateinit var viewModel: PrizeViewModel
    private lateinit var viewModelFactory: PrizeViewModelFactory

    override fun initView() {

        binding.apply {
            dataViewModel = viewModel
            fragment = this@PrizeFragment

            val latestDrwNo = latestDrwNo()
            val drwNoArray = ArrayList<Long>()
            for (i in latestDrwNo downTo 1) {
                drwNoArray.add(i)
            }

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, drwNoArray)
            spinner.setSelection(0)
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {
                            arrowForward.visibility = View.INVISIBLE
                        }
                        drwNoArray.lastIndex -> {
                            arrowBack.visibility = View.INVISIBLE
                        }
                        else -> {
                            arrowBack.visibility = View.VISIBLE
                            arrowForward.visibility = View.VISIBLE
                        }
                    }

                    viewModel.drwNo.value = spinner.selectedItem.toString().toLong()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        viewModelFactory = PrizeViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(PrizeViewModel::class.java)

        viewModel.drwNo.observe(this) {
            viewModel.getLottoWinnerNumber()
        }

        viewModel.lotteryData.observe(this) {
            binding.winningNumberLayout.apply {
                setLotteryNumbersColor(fifthNumberShape, it.drwtNo1)
                setLotteryNumbersColor(secondNumberShape, it.drwtNo2)
                setLotteryNumbersColor(thirdNumberShape, it.drwtNo3)
                setLotteryNumbersColor(fourthNumberShape, it.drwtNo4)
                setLotteryNumbersColor(fifthNumberShape, it.drwtNo5)
                setLotteryNumbersColor(sixthNumberShape, it.drwtNo6)
                setLotteryNumbersColor(bonusNumberShape, it.bnusNo)
            }
        }
    }

    private fun setLotteryNumbersColor(v: View, number: Int) {
        when (number) {
            in 1..9 -> v.background = resources.getDrawable(R.color.number_bg_1, null)
            in 10..19 -> v.background = resources.getDrawable(R.color.number_bg_2, null)
            in 20..29 -> v.background = resources.getDrawable(R.color.number_bg_3, null)
            in 30..39 -> v.background = resources.getDrawable(R.color.number_bg_4, null)
            in 40..45 -> v.background = resources.getDrawable(R.color.number_bg_5, null)
        }
    }

    fun goWinnerPage(v: View) {
        findNavController().navigate(R.id.actionWinnerFragment)
    }

    fun beforeSearchWinning(v: View) {
        binding.spinner.apply {
            setSelection(selectedItemPosition+1)
        }
    }

    fun nextSearchWinning(v: View) {
        binding.spinner.apply {
            setSelection(selectedItemPosition-1)
        }
    }

    companion object {
        fun newInstance() = PrizeFragment().apply { }
    }
}