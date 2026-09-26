package com.example.khmt2411017_nguyentanloc

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class TransactionAdapter(private val list: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val cardIconBg: CardView = itemView.findViewById(R.id.cardIconBg)
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
        holder.tvCategory.text = item.category
        holder.tvDate.text = item.date

        if (item.isExpense) {
            holder.tvAmount.text = "-${formatter.format(item.amount)} đ"
            holder.tvAmount.setTextColor(Color.parseColor("#DC2626"))
        } else {
            holder.tvAmount.text = "+${formatter.format(item.amount)} đ"
            holder.tvAmount.setTextColor(Color.parseColor("#16A34A"))
        }

        // Đổi màu nền icon tròn theo danh mục
        val bgHex = when (item.category) {
            "Ăn uống" -> "#F97316"   // Cam
            "Di chuyển" -> "#0284C7"  // Xanh dương
            "Thu nhập" -> "#16A34A"   // Xanh lá
            "Tiền lương" -> "#16A34A"
            "Mua sắm" -> "#A855F7"   // Tím
            "Giáo dục" -> "#0D9488"  // Xanh ngọc
            else -> "#64748B"
        }
        holder.cardIconBg.setCardBackgroundColor(Color.parseColor(bgHex))
    }

    override fun getItemCount(): Int = list.size
}