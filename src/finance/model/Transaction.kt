package finance.model

import java.time.LocalDate
import java.util.*

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val transactionType: TransactionType,
    val amount:Int,
    val category: String,
    val date:LocalDate,

    )
enum class TransactionType {
    INCOME,
    EXPENSE
}
