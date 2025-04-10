import finance.dataSource.TransactionDataSourceImp
import finance.model.Transaction
import finance.model.TransactionType
import java.time.LocalDate


fun main() {
    val dataSource = TransactionDataSourceImp()


    val transaction1 = Transaction(
        id = "1",
        transactionType = TransactionType.INCOME,
        amount = 6000,
        category = "salary",
        date = LocalDate.of(2025, 4, 8)
    )

    val fakeTransaction = Transaction(
        id = "800",
        transactionType = TransactionType.INCOME,
        amount = 6000000,
        category = "salary",
        date = LocalDate.of(2029, 2, 12)
    )

    val editedTransaction = transaction1.copy(amount = 7000)

    testTransaction(
        name = "Add Transaction1 should return the added transaction",
        result = dataSource.addTransaction(transaction1),
        correctResult = transaction1
    )

    testTransaction("Edit an existing transaction should return true",
        result = dataSource.editTransaction(editedTransaction),
        correctResult = true
    )

    testTransaction("Edit non existing transaction should return true",
        result = dataSource.editTransaction(fakeTransaction),
        correctResult = false
    )

    testTransaction(
        name = "Delete an existing transaction is valid",
        result = dataSource.deleteTransaction("1"),
        correctResult = true
    )

    testTransaction(
        name = "Delete non existing transaction is not valid",
        result = dataSource.deleteTransaction("999"),
        correctResult = false
    )
}

fun<T> testTransaction(name: String, result: T, correctResult: T) {
    if (result == correctResult) {
        println("Succeed: $name")
    } else {
        println("Failed: $name")
    }
}

