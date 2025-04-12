package finance.dataSource

import finance.model.Transaction
import kotlin.collections.set


class TransactionDataSourceImp(
    private val fileTransactionDataSource: FileTransactionDataSource
) : TransactionDataSource {
    private var transactions: MutableMap<String, Transaction> = mutableMapOf()

    init {
        // load local transactions
        transactions = fileTransactionDataSource.loadTransactionsFromFile()
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        fileTransactionDataSource.saveTransactionToFile(transaction)
        return true
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {

        return if (checkIfTransactionExist(editedTransaction.id)) {
            transactions[editedTransaction.id] = editedTransaction
            fileTransactionDataSource.saveTransactionToFile(editedTransaction, isEdited = true)
            true
        } else {
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return if (checkIfTransactionExist(id)) {
            transactions.remove(id)
            fileTransactionDataSource.deleteTransactionFromFile(id)
            true
        } else {
            false
        }
    }

    override fun getAllTransactions(): List<Transaction> = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]


    private fun checkIfTransactionExist(id: String) = transactions.containsKey(id)
}