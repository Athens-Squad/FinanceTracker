package finance.dataSource

import finance.model.BalanceReport
import finance.model.MonthSummary
import finance.model.Transaction
import java.time.YearMonth

interface TransactionDataSource {
    fun addTransaction(transaction: Transaction) : Boolean
    fun editTransaction(editedTransaction: Transaction) : Boolean
    fun deleteTransaction(id : String) : Boolean
    fun getAllTransactions() : List<Transaction>
    fun getTransactionById (id : String): Transaction?
    fun getMonthlySummary (month : YearMonth) : MonthSummary
}