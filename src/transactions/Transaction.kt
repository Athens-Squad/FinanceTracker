package transactions

import java.time.LocalDate
import java.util.*

enum class TransactionType {
    INCOME, EXPENSE
}

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val date: LocalDate,
    val category: String,
    val type: TransactionType
)

interface TransactionManager {
    fun addTransaction(transaction: Transaction)

    fun editTransaction(
        id: UUID,
        editedTransaction: Transaction
    ): Boolean

    fun deleteTransaction(id: UUID): Boolean

    fun getAllTransactions(): List<Transaction>
}