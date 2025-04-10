package finance.dataSource

import finance.model.MonthSummary
import finance.model.Transaction
import java.time.YearMonth


class TransactionDataSourceImp : TransactionDataSource {
    private val transactions: MutableMap<String, Transaction> = mutableMapOf()


    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        return true
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {

        return if (checkIfTransactionExist(editedTransaction.id)) {
            transactions[editedTransaction.id] = editedTransaction
            true
        } else {
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return if (checkIfTransactionExist(id)) {
            transactions.remove(id)
            true
        } else {
            false
        }
    }

    override fun getAllTransactions(): List<Transaction> = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]



    private fun checkIfTransactionExist(id: String) =
        transactions.containsKey(id)
}