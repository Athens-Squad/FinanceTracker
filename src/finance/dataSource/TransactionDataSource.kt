package finance.dataSource

import finance.model.BalanceReport
import finance.model.MonthSummary
import finance.model.Transaction

interface TransactionDataSource {
    fun addTransaction(transaction: Transaction) : Transaction
    fun editTransaction(editedTransaction: Transaction) : Boolean
    fun deleteTransaction(id : Int) : Boolean
    fun getAllTransactions() : List<Transaction>
    fun getTransactionById (id : Int): Transaction
    fun getMonthlySummary () : MonthSummary
    fun getBalanceReport () : BalanceReport

}