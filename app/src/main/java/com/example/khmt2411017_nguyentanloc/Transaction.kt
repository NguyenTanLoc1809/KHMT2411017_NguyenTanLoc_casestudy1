package com.example.khmt2411017_nguyentanloc

data class Transaction(
    val title: String,
    val amount: Double,
    val date: String,
    val isExpense: Boolean,
    val category: String
)

object TransactionRepository {
    val list = mutableListOf(
        Transaction("Ăn trưa", 50000.0, "03/09/2024", true, "Ăn uống"),
        Transaction("Xăng xe", 100000.0, "03/09/2024", true, "Di chuyển"),
        Transaction("Lương tháng 9", 8000000.0, "01/09/2024", false, "Thu nhập"),
        Transaction("Mua sắm", 300000.0, "31/08/2024", true, "Mua sắm"),
        Transaction("Học phí", 500000.0, "30/08/2024", true, "Giáo dục")
    )

    fun addTransaction(item: Transaction) {
        list.add(0, item)
    }

    fun getTotalIncome(): Double = list.filter { !it.isExpense }.sumOf { it.amount }
    fun getTotalExpense(): Double = list.filter { it.isExpense }.sumOf { it.amount }
    fun getTotalBalance(): Double = getTotalIncome() - getTotalExpense()
}