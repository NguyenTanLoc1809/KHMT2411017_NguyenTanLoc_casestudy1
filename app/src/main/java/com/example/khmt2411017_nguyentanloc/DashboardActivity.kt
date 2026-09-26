package com.example.khmt2411017_nguyentanloc

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat

class DashboardActivity : AppCompatActivity() {

    private lateinit var tvTotalBalance: TextView
    private lateinit var tvIncome: TextView
    private lateinit var tvExpense: TextView
    private lateinit var rvTransactions: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()

        tvTotalBalance = findViewById(R.id.tvTotalBalance)
        tvIncome = findViewById(R.id.tvIncome)
        tvExpense = findViewById(R.id.tvExpense)
        rvTransactions = findViewById(R.id.rvTransactions)

        // Cài đặt dạng danh sách cuộn dọc
        rvTransactions.layoutManager = LinearLayoutManager(this)

        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd?.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateDashboardData()
    }

    private fun updateDashboardData() {
        val formatter = DecimalFormat("#,###")
        val totalBalance = TransactionRepository.getTotalBalance()
        val totalIncome = TransactionRepository.getTotalIncome()
        val totalExpense = TransactionRepository.getTotalExpense()

        tvTotalBalance.text = "${formatter.format(totalBalance)} đ"
        tvIncome.text = "+${formatter.format(totalIncome)} đ"
        tvExpense.text = "-${formatter.format(totalExpense)} đ"

        // Nạp danh sách giao dịch mới nhất ra màn hình
        rvTransactions.adapter = TransactionAdapter(TransactionRepository.list)
    }
}