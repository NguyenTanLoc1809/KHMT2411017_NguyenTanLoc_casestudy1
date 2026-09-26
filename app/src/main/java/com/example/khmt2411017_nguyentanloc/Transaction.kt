package com.example.khmt2411017_nguyentanloc

data class Transaction(
    val title: String,
    val amount: Double,
    val date: String,
    val isExpense: Boolean, // true: Chi tiêu, false: Thu nhập
    val category: String
)

object TransactionRepository {
    val list = mutableListOf(
        Transaction("Lương tháng", 20000000.0, "01/09/2026", false, "Tiền lương"),
        Transaction("Ăn uống", 4500000.0, "15/09/2026", true, "Ăn uống")
    )

    fun addTransaction(item: Transaction) {
        list.add(0, item)
    }

    fun getTotalIncome(): Double = list.filter { !it.isExpense }.sumOf { it.amount }
    fun getTotalExpense(): Double = list.filter { it.isExpense }.sumOf { it.amount }
    fun getTotalBalance(): Double = getTotalIncome() - getTotalExpense()
}