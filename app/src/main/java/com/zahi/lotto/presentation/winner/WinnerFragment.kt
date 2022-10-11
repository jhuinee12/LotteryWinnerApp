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
import com.zahi.lotto.entity.LotteryItem
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.util.findLatestDrwNo

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

    private var drwNo: Long = 0
    private val nums = arrayListOf<Int>()

    override fun initView() {

        binding.apply {
            dataViewModel = viewModel

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
                nums.clear()

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
                    // 복권 당첨 금액 확인
                    viewModel.getLottoWinnerNumber(drwNo, "https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo=$drwNo")
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)

        viewModel.lotteryItem.observe(this) {
            calcRank(it)
        }
    }

    private fun calcRank(lottery: ArrayList<LotteryItem>) {
        var correctNumber = 0

        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo1)) correctNumber++
        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo2)) correctNumber++
        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo3)) correctNumber++
        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo4)) correctNumber++
        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo5)) correctNumber++
        if (nums.contains(viewModel.lotteryNumber.value!!.drwtNo6)) correctNumber++

        calcPrize(correctNumber, lottery)
    }

    private fun calcPrize(correctNumber: Int, lottery: ArrayList<LotteryItem>) {
        binding.prize.text = "당첨금 : "+ when (correctNumber) {
            6 -> {   // 1등 당첨
                lottery[0].prize
            }
            5 -> {
                if (nums.contains(viewModel.lotteryNumber.value!!.bnusNo)) { // 2등 당첨
                    lottery[1].prize
                } else { // 3등 당첨
                    lottery[2].prize
                }
            }
            4 -> {  // 4등 당첨
                lottery[3].prize
            }
            3 -> {  // 5등 당첨
                lottery[4].prize
            }
            else -> "0원"
        }

        binding.layoutPrize.visibility = View.VISIBLE
    }
}