package com.zahi.lotto.presentation.recommended

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentRecommendedBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.base.BaseFragment

class RecommendedFragment : BaseFragment<FragmentRecommendedBinding>(R.layout.fragment_recommended) {

    private lateinit var viewModel: RecommendedViewModel
    private lateinit var viewModelFactory: RecommendedViewModelFactory

    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    private val recommendedArray = arrayListOf<Int>()

    override fun initView() {
        activity.changeToolbar(title = "추천 번호", true)

        binding.apply {
            this.btnRecommended.setOnClickListener {
                for (i in 0..5) {
                    while (true) {
                        if (addRecommendedNumber()) break
                    }
                }

                this.layoutRecommendedNumber.visibility = View.VISIBLE

                recommendedArray.sort()

                this.firstRecommendedNumber.text = recommendedArray[0].toString()
                this.secondRecommendedNumber.text = recommendedArray[1].toString()
                this.thirdRecommendedNumber.text = recommendedArray[2].toString()
                this.fourthRecommendedNumber.text = recommendedArray[3].toString()
                this.fifthRecommendedNumber.text = recommendedArray[4].toString()
                this.sixthRecommendedNumber.text = recommendedArray[5].toString()
            }
        }
    }

    override fun initViewModel() {
        viewModelFactory = RecommendedViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecommendedViewModel::class.java)
    }

    private fun createRandomNumber() = (1..45).random()

    private fun addRecommendedNumber(): Boolean {
        val random = createRandomNumber()
        return if (!recommendedArray.contains(random)) {
            recommendedArray.add(random)
            true
        } else {
            false
        }
    }

    companion object {
        fun newInstance() = RecommendedFragment().apply { }
    }
}