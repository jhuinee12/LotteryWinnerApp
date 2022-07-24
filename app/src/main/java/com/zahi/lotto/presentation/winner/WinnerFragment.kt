package com.zahi.lotto.presentation.winner

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.base.BaseFragment
import com.zahi.lotto.databinding.FragmentWinnerBinding
import com.zahi.lotto.util.findLatestDrwNo
import java.util.ArrayList

class WinnerFragment : BaseFragment<FragmentWinnerBinding>(R.layout.fragment_winner) {

    private lateinit var viewModel: WinnerViewModel
    private lateinit var viewModelFactory: WinnerViewModelFactory

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

                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) { }
            }

            this.btnSearchWinner.setOnClickListener {
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = WinnerViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(WinnerViewModel::class.java)
    }

    companion object {
        fun newInstance() = WinnerFragment().apply { }
    }
}