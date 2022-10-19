package com.zahi.lotto.presentation.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
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
        viewModelFactory = PrizeViewModelFactory(WinnerRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(PrizeViewModel::class.java)

        viewModel.drwNo.observe(this) {
            viewModel.getLottoWinnerNumber()
        }
    }

    companion object {
        fun newInstance() = PrizeFragment().apply { }
    }
}