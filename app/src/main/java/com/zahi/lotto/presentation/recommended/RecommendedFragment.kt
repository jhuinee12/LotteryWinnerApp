package com.zahi.lotto.presentation.recommended

import androidx.recyclerview.widget.LinearLayoutManager
import com.zahi.lotto.R
import com.zahi.lotto.databinding.FragmentRecommendedBinding
import com.zahi.lotto.presentation.MainActivity
import com.zahi.lotto.base.BaseFragment

class RecommendedFragment : BaseFragment<FragmentRecommendedBinding>(R.layout.fragment_recommended) {

    private lateinit var recommendedAdapter: RecommendedAdapter

    private val activity: MainActivity by lazy { requireActivity() as MainActivity }

    override fun initView() {
        activity.changeToolbar(title = "추천 번호", true)

        recommendedAdapter = RecommendedAdapter(requireContext())

        binding.apply {
            btnRecommended.setOnClickListener {
                val numbers = arrayListOf<Int>()
                for (i in 0..5) {
                    numbers.add(addRecommendedNumber(numbers))
                }
                numbers.sort()
                recommendedAdapter.update(numbers)
            }

            recyclerview.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = recommendedAdapter
            }
        }
    }

    override fun initViewModel() { }

    private fun createRandomNumber() = (1..45).random()

    private fun addRecommendedNumber(numbers: ArrayList<Int>): Int {
        var random: Int
        while (true) {
            random = createRandomNumber()
            if (!numbers.contains(random)) {
                break
            }
        }
        return random
    }

    companion object {
        fun newInstance() = RecommendedFragment().apply { }
    }
}