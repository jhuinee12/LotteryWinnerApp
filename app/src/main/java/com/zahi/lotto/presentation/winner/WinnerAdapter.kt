package com.zahi.lotto.presentation.winner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zahi.lotto.R
import com.zahi.lotto.databinding.ItemInputNumberBinding

class WinnerAdapter: RecyclerView.Adapter<WinnerAdapter.WinnerViewHolder>() {

    private var numbers: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerViewHolder =
        WinnerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_input_number, parent, false))

    override fun onBindViewHolder(holder: WinnerViewHolder, position: Int) {
        holder.binding.numbers = numbers[position]

        holder.binding.btnDelete.setOnClickListener {
            numbers.removeAt(position)
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = numbers.size

    fun update (updated: ArrayList<Int>) {
        numbers.add(updated)
        this.notifyDataSetChanged()
    }

    inner class WinnerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemInputNumberBinding.bind(view)
    }
}