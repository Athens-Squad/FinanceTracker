package finance.logic

import finance.dataSource.TransactionDataSource
import finance.model.MonthSummary
import finance.model.Transaction
import finance.model.TransactionType
import java.time.YearMonth

class TransactionManagerImpl(
    private val dataSource: TransactionDataSource
): TransactionManager, TransactionDataSource by dataSource {

    override fun getSummaryMonth(month: YearMonth): MonthSummary {
        val transactions = getAllTransactions()
        val monthlyTransactions = transactions.filter { transaction ->
            YearMonth.from(transaction.date) == month
        }
        val income = monthlyTransactions.filter { it.transactionType == TransactionType.INCOME }
            .sumOf { it.amount }

        val expense = monthlyTransactions.filter { it.transactionType == TransactionType.EXPENSE }
            .sumOf { it.amount }

        return MonthSummary(
            transactions = monthlyTransactions,
            balance = income - expense,
            income = income,
            expense = expense
        )
    }
}