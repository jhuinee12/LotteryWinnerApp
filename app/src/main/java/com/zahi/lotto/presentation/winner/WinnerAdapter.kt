package com.zahi.lotto.presentation.winner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.zahi.lotto.R
import com.zahi.lotto.databinding.ItemInputNumberBinding
import com.zahi.lotto.entity.LotteryItem
import com.zahi.lotto.entity.LotteryNumber

class WinnerAdapter(c: Context): RecyclerView.Adapter<WinnerAdapter.WinnerViewHolder>() {

    private val context = c
    private var numbers: ArrayList<ArrayList<Int>> = arrayListOf()

    var lotteryItem: ArrayList<LotteryItem> = arrayListOf()
    var lotteryWinningNums: ArrayList<Int> = arrayListOf()
    var myPrize: Long = 0L
    var isShowGrade = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerViewHolder =
        WinnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_input_number, parent, false))

    override fun onBindViewHolder(holder: WinnerViewHolder, position: Int) {

        holder.binding.apply {
            getNumber = numbers[position]
            showGrade = isShowGrade
            this.position = position+1

            delete.setOnClickListener {
                numbers.removeAt(position)
                notifyDataSetChanged()
            }

            if (isShowGrade) {
                if (lotteryItem.isNotEmpty() && lotteryWinningNums.isNotEmpty()) {
                    var correctCount = 0
                    var isBonus = false

                    var pair = isCorrectMyNum(numbers[position][0], firstNumberShape, firstNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    pair = isCorrectMyNum(numbers[position][1], secondNumberShape, secondNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    pair = isCorrectMyNum(numbers[position][2], thirdNumberShape, thirdNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    pair = isCorrectMyNum(numbers[position][3], fourthNumberShape, fourthNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    pair = isCorrectMyNum(numbers[position][4], fifthNumberShape, fifthNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    pair = isCorrectMyNum(numbers[position][5], sixthNumberShape, sixthNumberStar)
                    if (pair.first) correctCount++
                    if (pair.second) isBonus = true

                    this.rank = calcRank(correctCount, isBonus)
                }
            }
        }
    }

    override fun getItemCount(): Int = numbers.size

    fun update (updated: ArrayList<Int>) {
        numbers.add(updated)
        notifyDataSetChanged()
    }

    private fun setLotteryNumbersColor(v: View, number: Int) {
        when (number) {
            in 1..9 -> v.background = context.resources.getDrawable(R.color.number_bg_1, null)
            in 10..19 -> v.background = context.resources.getDrawable(R.color.number_bg_2, null)
            in 20..29 -> v.background = context.resources.getDrawable(R.color.number_bg_3, null)
            in 30..39 -> v.background = context.resources.getDrawable(R.color.number_bg_4, null)
            in 40..45 -> v.background = context.resources.getDrawable(R.color.number_bg_5, null)
        }
    }

    private fun isCorrectMyNum(num: Int, v: View, starV: View): Pair<Boolean, Boolean> {
        var isBonus = false
        var isCorrect = false

        if (lotteryWinningNums.contains(num)) {
            setLotteryNumbersColor(v, num)

            if (num == lotteryWinningNums[6]) {
                isBonus = true
                starV.visibility = View.VISIBLE
            }
            else isCorrect = true
        } else {
            v.background = context.resources.getDrawable(R.color.white, null)
        }

        return Pair(isCorrect, isBonus)
    }

    private fun calcRank(correctCount: Int, isBonus: Boolean): Int {
        when (correctCount) {
            6 -> {
                myPrize += lotteryItem[0].prize
                return 1
            }
            5 -> {
                if (isBonus) 2
                else 3
            }
            4 -> 4
            3 -> 5
            else -> 0
        }
    }

    inner class WinnerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemInputNumberBinding.bind(view)
    }
}