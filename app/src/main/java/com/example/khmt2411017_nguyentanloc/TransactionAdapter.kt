package com.example.khmt2411017_nguyentanloc

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class TransactionAdapter(private val list: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val formatter = DecimalFormat("#,###")

        holder.tvTitle.text = item.title
        holder.tvDate.text = item.date

        if (item.isExpense) {
            holder.tvAmount.text = "-${formatter.format(item.amount)} đ"
            holder.tvAmount.setTextColor(Color.parseColor("#F87171")) // Màu đỏ cho Chi tiêu
        } else {
            holder.tvAmount.text = "+${formatter.format(item.amount)} đ"
            holder.tvAmount.setTextColor(Color.parseColor("#4ADE80")) // Màu xanh cho Thu nhập
        }
    }

    override fun getItemCount(): Int = list.size
}