package com.example.khmt2411017_nguyentanloc

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Calendar

class AddTransactionActivity : AppCompatActivity() {

    private var isExpense = true // Mặc định là Chi tiêu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        supportActionBar?.hide()

        val btnExpense = findViewById<MaterialButton>(R.id.btnExpense)
        val btnIncome = findViewById<MaterialButton>(R.id.btnIncome)
        val tvCategoryName = findViewById<TextView>(R.id.tvCategoryName)
        val edtAmount = findViewById<EditText>(R.id.edtAmount)
        val edtDate = findViewById<EditText>(R.id.edtDate)
        val edtNote = findViewById<EditText>(R.id.edtNote)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Chuyển Tab Chi tiêu
        btnExpense?.setOnClickListener {
            isExpense = true
            btnExpense.setBackgroundColor(Color.parseColor("#FF5252"))
            btnExpense.setTextColor(Color.WHITE)
            btnIncome?.setBackgroundColor(Color.WHITE)
            btnIncome?.setTextColor(Color.parseColor("#64748B"))
            tvCategoryName?.text = "Ăn uống"
        }

        // Chuyển Tab Thu nhập
        btnIncome?.setOnClickListener {
            isExpense = false
            btnIncome.setBackgroundColor(Color.parseColor("#22C55E"))
            btnIncome.setTextColor(Color.WHITE)
            btnExpense?.setBackgroundColor(Color.WHITE)
            btnExpense?.setTextColor(Color.parseColor("#64748B"))
            tvCategoryName?.text = "Tiền lương"
        }

        // Chọn ngày
        edtDate?.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    edtDate.setText(String.format("%02d/%02d/%d", day, month + 1, year))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // Bấm Lưu
        btnSave?.setOnClickListener {
            val amountStr = edtAmount?.text?.toString()?.trim() ?: ""
            val noteStr = edtNote?.text?.toString()?.trim() ?: ""
            val dateStr = edtDate?.text?.toString()?.trim() ?: "Hôm nay"
            val categoryStr = tvCategoryName?.text?.toString() ?: "Khác"

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số tiền!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDoubleOrNull() ?: 0.0
            val title = if (noteStr.isNotEmpty()) noteStr else categoryStr

            TransactionRepository.addTransaction(
                Transaction(
                    title = title,
                    amount = amount,
                    date = dateStr,
                    isExpense = isExpense,
                    category = categoryStr
                )
            )

            Toast.makeText(this, "Đã lưu giao dịch!", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnBack?.setOnClickListener { finish() }
    }
}