package finance.dataSource

import finance.model.BalanceReport
import finance.model.MonthSummary
import finance.model.Transaction


class TransactionDataSourceImp : TransactionDataSource {
    private val transactions : MutableMap<String,Transaction> = mutableMapOf()


    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        return true
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {

        return if(checkIfTransactionExist(editedTransaction.id)){
            transactions[editedTransaction.id] = editedTransaction
            true
        } else{
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return if(checkIfTransactionExist(id)){
            transactions.remove(id)
            true
        } else{
            false
        }
    }

    override fun getAllTransactions(): List<Transaction>  = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]



    override fun getMonthlySummary(month : Int): MonthSummary {
        val monthTransactions = transactions.filter { it.date.month == month }
        return MonthSummary(
            month = month
        )
    }

    // filter by category
    override fun getBalanceReport(): BalanceReport {
        val income = transactions.filter { it.type ==TransactionsType.INCOME }.sumOf { it.quantity }
        val expense = transactions.filter { it.type == TransactionsType.EXPENSE }.sumOf { it.quantity }
        return BalanceReport(
            income, expense
        )
    }

    private fun checkIfTransactionExist(id : String) =
        transactions.containsKey(id)
}