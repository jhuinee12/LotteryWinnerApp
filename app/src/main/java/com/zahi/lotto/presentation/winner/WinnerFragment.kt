package com.zahi.lotto.presentation.winner

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zahi.lotto.R
import com.zahi.lotto.base.BaseFragment
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.util.findLatestDrwNo

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory
    private lateinit var winnerAdapter: WinnerAdapter

    private var drwNo: Long = 0

    override fun initView() {

        winnerAdapter = WinnerAdapter(requireContext())

        binding.apply {
            fragment = this@WinnerFragment
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

            recyclerview.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = winnerAdapter
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)

        viewModel.lotteryItem.observe(this) {
            winnerAdapter.lotteryItem = it
            winnerAdapter.notifyDataSetChanged()
        }
        viewModel.lotteryNumber.observe(this) {
            winnerAdapter.lotteryWinningNums.add(it.drwtNo1)
            winnerAdapter.lotteryWinningNums.add(it.drwtNo2)
            winnerAdapter.lotteryWinningNums.add(it.drwtNo3)
            winnerAdapter.lotteryWinningNums.add(it.drwtNo4)
            winnerAdapter.lotteryWinningNums.add(it.drwtNo5)
            winnerAdapter.lotteryWinningNums.add(it.drwtNo6)
            winnerAdapter.lotteryWinningNums.add(it.bnusNo)
        }
    }

    fun addNumbers(v: View) {
        val keyboard = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(requireView().windowToken, 0)

        binding.inputNumber.apply {

            if (firstNumber.text.isNotEmpty()
                && secondNumber.text.isNotEmpty()
                && thirdNumber.text.isNotEmpty()
                && fourthNumber.text.isNotEmpty()
                && fifthNumber.text.isNotEmpty()
                && sixthNumber.text.isNotEmpty()) {

                val nums: Set<Int> = setOf(
                    firstNumber.text.toString().toInt(),
                    secondNumber.text.toString().toInt(),
                    thirdNumber.text.toString().toInt(),
                    fourthNumber.text.toString().toInt(),
                    fifthNumber.text.toString().toInt(),
                    sixthNumber.text.toString().toInt()
                )

                val array = nums.toCollection(arrayListOf())
                if (nums.size != 6) {
                    toast("6개의 숫자를 중복되지 않게 입력해주세요.")
                } else {
                    array.sort()
                    winnerAdapter.update(array)
                }

            } else {
                toast("6개의 숫자를 모두 입력해주세요.")
            }
        }
    }

    fun searchWinning (v: View) {
        binding.apply {
            viewModel.getLottoWinnerNumber(drwNo, "https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo=$drwNo")

            btnSearchWinner.visibility = View.GONE
            btnModifyNumber.visibility = View.VISIBLE
            inputNumberLayout.visibility = View.GONE

            winnerAdapter.isShowGrade = true
        }
    }

    fun modifyNumbers (v: View) {
        binding.apply {
            btnSearchWinner.visibility = View.VISIBLE
            btnModifyNumber.visibility = View.GONE
            inputNumberLayout.visibility = View.VISIBLE

            winnerAdapter.isShowGrade = false
            winnerAdapter.notifyDataSetChanged()

            winnerAdapter.lotteryItem = arrayListOf()
            winnerAdapter.lotteryWinningNums = arrayListOf()
        }
    }
}