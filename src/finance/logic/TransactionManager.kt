package finance.logic

import finance.model.MonthSummary
import finance.model.Transaction
import java.time.YearMonth

// all functions
interface TransactionManager {

    fun addTransaction(transaction: Transaction)

    fun deleteTransaction(id: String)

    fun editTransaction(transaction: Transaction)

    fun getAllTransactions(): List<Transaction>

    fun getTransactionById(id: String): Transaction?

    //transaction list + balance + income +
    fun getSummaryMonth(month: YearMonth): MonthSummary
}