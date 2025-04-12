import finance.dataSource.FileTransactionDataSourceImpl
import finance.dataSource.TransactionDataSource
import finance.logic.TransactionManager
import finance.logic.TransactionManagerImpl
import finance.model.Transaction
import finance.model.TransactionType
import java.time.LocalDate

fun main() {

    val transaction1 = Transaction("1", TransactionType.INCOME, 5000, "Salary", LocalDate.of(2025, 4, 1))
    val transaction2 = Transaction("2", TransactionType.EXPENSE, 1000, "Rent", LocalDate.of(2025, 4, 3))
    val transaction3 = Transaction("3", TransactionType.INCOME, 1500, "Bonus", LocalDate.of(2025, 4, 10))
    val newTransaction3 = Transaction("3", TransactionType.EXPENSE, 500, "Electricity", LocalDate.of(2025, 4, 10))

    val fileTransactionDataSource: TransactionDataSource = FileTransactionDataSourceImpl()
    val transactionManager: TransactionManager = TransactionManagerImpl(fileTransactionDataSource)

    transactionManager.addTransaction(transaction1)
    transactionManager.addTransaction(transaction2)
    transactionManager.addTransaction(transaction3)

//
//    transactionManager.deleteTransaction(transaction2.id)
//
//    transactionManager.editTransaction(newTransaction3)

}
