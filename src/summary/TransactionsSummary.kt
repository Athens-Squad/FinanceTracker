package summary

import java.time.YearMonth

interface TransactionsSummary {

    fun getBalance(): Double

    fun getMonthlySummary(
        month: YearMonth
    ): Map<String, Double>

}