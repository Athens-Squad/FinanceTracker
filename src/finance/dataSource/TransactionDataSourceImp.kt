package finance.dataSource

import finance.dataSource.mapper.toFileTransaction
import finance.dataSource.mapper.toTransaction
import finance.dataSource.model.FileTransaction
import finance.model.Transaction
import java.io.File
import kotlin.collections.set

class TransactionDataSourceImp(
    private val filePath: String = "transactions.txt"
) : TransactionDataSource {
    private var transactions: MutableMap<String, Transaction> = mutableMapOf()

    init {
        loadTransactionsFromFile()
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        saveTransactionsToFile()
        return true
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {

        return if (checkIfTransactionExist(editedTransaction.id)) {
            transactions[editedTransaction.id] = editedTransaction
            saveTransactionsToFile()
            true
        } else {
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return if (checkIfTransactionExist(id)) {
            transactions.remove(id)
            saveTransactionsToFile()
            true
        } else {
            false
        }
    }

    override fun getAllTransactions(): List<Transaction> = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]

    override fun saveTransactionsToFile(): Boolean {
        return try {
            val file = File(filePath)

            val updatedTransactions = transactions.values.joinToString("\n") { transaction ->
                transaction
                    .toFileTransaction()
                    .toFileContent()
            }

            file.writeText(updatedTransactions)
            true

        } catch (e: Exception) {

            println("Error saving transactions: ${e.message}")
            false
        }
    }


    override fun loadTransactionsFromFile(): Boolean {
        val file = File(filePath)

        if (!file.exists()) {
            return false
        }

        return try {
            file.forEachLine { transactionLine ->
                val transaction = FileTransaction
                    .parseTransactionFromString(transactionLine)
                    .toTransaction()

                transactions[transaction.id] = transaction
            }
            true
        } catch (e: Exception) {

            println("Error loading transactions: ${e.message}")
            false
        }
    }


    private fun checkIfTransactionExist(id: String) = transactions.containsKey(id)
}