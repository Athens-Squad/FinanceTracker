package finance.dataSource.model

import finance.model.TransactionType
import java.util.*

data class FileTransaction(
    val id: String = UUID.randomUUID().toString(),
    val amount: Int,
    val category: String,
    val dateFormatted: String,
    val transactionType: TransactionType
) {
    companion object Parser {
        fun parseTransactionFromString(transactionLine: String): FileTransaction {
            val transactionParts = transactionLine.split("|")

            return FileTransaction(
                id = transactionParts[0],
                amount = transactionParts[1].toIntOrNull() ?: 0,
                category = transactionParts[2],
                dateFormatted = transactionParts[3],
                transactionType = TransactionType.valueOf(transactionParts[4])
            )
        }
    }

    fun toFileContent(): String =
        "${this.id}|${this.amount}|${this.category}|$dateFormatted|${this.transactionType}"
}