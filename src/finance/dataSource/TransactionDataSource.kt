package finance.dataSource

import finance.model.Transaction

interface TransactionDataSource {
    fun addTransaction(transaction: Transaction) : Boolean
    fun editTransaction(editedTransaction: Transaction) : Boolean
    fun deleteTransaction(id : String) : Boolean
    fun getAllTransactions() : List<Transaction>
    fun getTransactionById (id : String): Transaction?
}