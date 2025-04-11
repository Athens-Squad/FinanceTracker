package finance.dataSource.mapper

import finance.dataSource.model.FileTransaction
import finance.model.Transaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun FileTransaction.toTransaction(): Transaction {
    return Transaction(
        id = this.id,
        transactionType = this.transactionType,
        amount = this.amount,
        category = this.category,
        date = LocalDate.parse(this.dateFormatted)
    )
}

fun Transaction.toFileTransaction(): FileTransaction {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return FileTransaction(
        id = this.id,
        transactionType = this.transactionType,
        amount = this.amount,
        category = this.category,
        dateFormatted = this.date.format(dateFormatter)
    )
}
