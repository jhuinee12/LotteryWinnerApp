package com.zahi.lotto.presentation.winner

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.base.BaseFragment
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.util.findLatestDrwNo

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

    private var drwNo: Long = 0
    private val nums = arrayListOf<Int>()

    override fun initView() {

        binding.apply {

            val latestDrwNo = findLatestDrwNo.latestDrwNo()
            val drwNoArray = ArrayList<Long>()
            for (i in latestDrwNo downTo 1) {
                drwNoArray.add(i)
            }

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, drwNoArray)
            spinner.setSelection(0)
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    drwNo = spinner.selectedItem.toString().toLong()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { }
            }

            this.btnSearchWinner.setOnClickListener {
                val keyboard = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(requireView().windowToken, 0)

                if (this.inputFirstNumber.text.isNotEmpty()
                    && this.inputSecondNumber.text.isNotEmpty()
                    && this.inputThirdNumber.text.isNotEmpty()
                    && this.inputFourthNumber.text.isNotEmpty()
                    && this.inputFifthNumber.text.isNotEmpty()
                    && this.inputSixthNumber.text.isNotEmpty()) {

                    nums.add(this.inputFirstNumber.text.toString().toInt())
                    nums.add(this.inputSecondNumber.text.toString().toInt())
                    nums.add(this.inputThirdNumber.text.toString().toInt())
                    nums.add(this.inputFourthNumber.text.toString().toInt())
                    nums.add(this.inputFifthNumber.text.toString().toInt())
                    nums.add(this.inputSixthNumber.text.toString().toInt())
                } else {
                    toast("6개의 숫자를 모두 입력해주세요.")
                    return@setOnClickListener
                }

                nums.distinct()
                if (nums.size != 6) {
                    toast("6개의 숫자를 중복되지 않게 입력해주세요.")
                } else {
                    viewModel.getLottoWinnerNumber(drwNo)
                }
            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)

        viewModel.lotteryData.observe(this) {

            binding.winnerFirstNumber.text = it.drwtNo1.toString()
            binding.winnerSecondNumber.text = it.drwtNo2.toString()
            binding.winnerThirdNumber.text = it.drwtNo3.toString()
            binding.winnerFourthNumber.text = it.drwtNo4.toString()
            binding.winnerFifthNumber.text = it.drwtNo5.toString()
            binding.winnerSixthNumber.text = it.drwtNo6.toString()
            binding.winnerBonusNumber.text = it.bnusNo.toString()

            var correctNumber = 0

            if (nums.contains(it.drwtNo1)) correctNumber++
            if (nums.contains(it.drwtNo2)) correctNumber++
            if (nums.contains(it.drwtNo3)) correctNumber++
            if (nums.contains(it.drwtNo4)) correctNumber++
            if (nums.contains(it.drwtNo5)) correctNumber++
            if (nums.contains(it.drwtNo6)) correctNumber++

            binding.prize.text = "당첨금 : "+ when (correctNumber) {
                6 -> it.firstAccumamnt.toString()
                else -> "0"
            } + "원"
        }
    }

    companion object {
        fun newInstance() = WinnerFragment().apply { }
    }
}