package finance.logic

import finance.dataSource.TransactionDataSource
import finance.model.MonthSummary
import finance.model.Transaction
import java.time.YearMonth

// all functions
interface TransactionManager: TransactionDataSource {

    //transaction list + balance + income +
    fun getSummaryMonth(month: YearMonth): MonthSummary
}