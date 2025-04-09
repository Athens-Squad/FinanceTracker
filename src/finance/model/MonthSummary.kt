package finance.model

data class MonthSummary(
    val transactions: List<Transaction>,
    val balance: Int,
    val income: Int,
    val expense: Int
)
