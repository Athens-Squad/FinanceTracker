package finance.dataSource

import finance.model.Transaction
import finance.model.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TransactionParser {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun parseLine(line: String): Transaction? {
        val parts = line.split("|")
        if (parts.size != 5) return null

        return try {
            val id = parts[0]
            val transactionType = TransactionType.valueOf(parts[4])
            val amount = parts[1].toIntOrNull() ?: return null
            val category = parts[2]
            val date = LocalDate.parse(parts[3], dateFormatter)

            Transaction(id, transactionType, amount, category, date)
        } catch (e: Exception) {
            null
        }
    }

    fun parseTransactionToLine(transaction: Transaction): String {
        return "${transaction.id}|${transaction.amount}|${transaction.category}|${transaction.date.format(dateFormatter)}|${transaction.transactionType}"

    }
}
