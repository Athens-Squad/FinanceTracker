package finance.dataSource

import finance.model.BalanceReport
import finance.model.MonthSummary
import finance.model.Transaction
import finance.model.TransactionType


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


    override fun getMonthlySummary(month: Int): MonthSummary {
        val monthTransactions = transactions.filter { it.value.date.monthValue == month }
        return MonthSummary(
            month = monthTransactions
        )
    }

    // filter by category
    override fun getBalanceReport(): BalanceReport {
        val listOfTransactions = getAllTransactions()
        val income = listOfTransactions.filter { it.transactionType == TransactionType.INCOME }.sumOf { it.amount }

        val expense = listOfTransactions.filter { it.transactionType == TransactionType.EXPENSE }.sumOf { it.amount }
        return BalanceReport(
            income, expense
        )
    }

    private fun checkIfTransactionExist(id: String) =
        transactions.containsKey(id)
}