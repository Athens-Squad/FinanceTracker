package data

import finance.dataSource.TransactionDataSource
import finance.model.Transaction

class MockDataSourceImpl : TransactionDataSource {
    private val transactions = mutableMapOf<String, Transaction>()

    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        return true
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {
        return if (transactions.containsKey(editedTransaction.id)) {
            transactions[editedTransaction.id] = editedTransaction
            true
        } else {
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return transactions.remove(id) != null
    }

    override fun getAllTransactions(): List<Transaction> = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]
}
