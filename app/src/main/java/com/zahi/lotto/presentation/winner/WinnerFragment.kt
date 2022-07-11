package com.zahi.lotto.presentation.winner

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.presentation.recommended.RecommendedFragment
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.lotto.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

    override fun initView() {

        (activity as MainActivity).changeToolbar(true, "로또 당첨 번호 조회")

        Log.d("TAG", "initView: latestDrwNo - ${latestDrwNo()}")

        binding.apply {
            dataViewModel = viewModel

            val latestDrwNo = latestDrwNo()
            val drwNoArray = ArrayList<Long>()
            for (i in latestDrwNo downTo 1) {
                drwNoArray.add(i)
            }

            spinner.adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, drwNoArray)
            spinner.setSelection(0)
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.drwNo.value = spinner.selectedItem.toString().toLong()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { }
            }

        }
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