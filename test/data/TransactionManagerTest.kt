import data.MockDataSourceImpl
import finance.logic.TransactionManagerImpl
import finance.model.Transaction
import finance.model.TransactionType
import java.time.LocalDate
import java.time.YearMonth

fun main() {
    val dataSource = MockDataSourceImpl()
    val manager = TransactionManagerImpl(dataSource)

    val transaction1 = Transaction("1", TransactionType.INCOME, 5000, "Salary", LocalDate.of(2025, 4, 1))
    val transaction2 = Transaction("2", TransactionType.EXPENSE, 1000, "Rent", LocalDate.of(2025, 4, 3))
    val transaction3 = Transaction("3", TransactionType.INCOME, 1500, "Bonus", LocalDate.of(2025, 4, 10))

    manager.addTransaction(transaction1)
    manager.addTransaction(transaction2)
    manager.addTransaction(transaction3)

    val updatedTransaction = transaction1.copy(amount = 6000)
    manager.editTransaction(updatedTransaction)

    manager.deleteTransaction("2")

    val transaction4 = Transaction("4", TransactionType.INCOME, 500, "Gift", LocalDate.of(2025, 4, 20))
    manager.addTransaction(transaction4)

    val summary = manager.getSummaryMonth(YearMonth.of(2025, 4))

    testTransactionManager("Total income should be 6000 + 1500 + 500 = 8000", summary.income, 8000)
    testTransactionManager("Total expense should be 0 (transaction2 deleted)", summary.expense, 0)
    testTransactionManager("Total balance should be 8000", summary.balance, 8000)
}

fun <T> testTransactionManager(name: String, result: T, correctResult: T) {
    if (result == correctResult) {
        println("Succeed: $name")
    } else {
        println("Failed: $name. Expected $correctResult but got $result")
    }
}