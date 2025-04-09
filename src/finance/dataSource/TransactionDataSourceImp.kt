package finance.dataSource

import finance.model.BalanceReport
import finance.model.MonthSummary
import finance.model.Transaction


class TransactionDataSourceImp : TransactionDataSource {
    override fun addTransaction(transaction: Transaction): Transaction {
        TODO("Not yet implemented")
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

    override fun getTransactionById(id: Int): Transaction {
        TODO("Not yet implemented")
    }

    override fun getMonthlySummary(): MonthSummary {
        TODO("Not yet implemented")
    }

    override fun getBalanceReport(): BalanceReport {
        TODO("Not yet implemented")
    }

}
