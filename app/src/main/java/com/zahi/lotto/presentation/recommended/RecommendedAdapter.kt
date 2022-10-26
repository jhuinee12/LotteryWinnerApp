package com.zahi.lotto.presentation.recommended

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zahi.lotto.R
import com.zahi.lotto.databinding.ItemRecommendedNumberBinding

class RecommendedAdapter(c: Context): RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder>() {
    private val context = c
    private var numbers: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewHolder =
        RecommendedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recommended_number, parent, false))

    override fun onBindViewHolder(holder: RecommendedViewHolder, position: Int) {
        holder.binding.apply {
            this.getNumber = numbers[position]
            this.position = position+1

            setLotteryNumbersColor(firstNumberShape, numbers[position][0])
            setLotteryNumbersColor(secondNumberShape, numbers[position][1])
            setLotteryNumbersColor(thirdNumberShape, numbers[position][2])
            setLotteryNumbersColor(fourthNumberShape, numbers[position][3])
            setLotteryNumbersColor(fifthNumberShape, numbers[position][4])
            setLotteryNumbersColor(sixthNumberShape, numbers[position][5])
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

    inner class RecommendedViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemRecommendedNumberBinding.bind(view)
    }
}