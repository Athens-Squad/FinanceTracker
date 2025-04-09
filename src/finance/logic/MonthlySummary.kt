import finance.dataSource.TransactionDataSource
import finance.model.MonthSummary
import java.time.YearMonth

class MonthlySummary(
    private val dataSource: TransactionDataSource
) {
    //takes the mont yyyy-mm and return Map<Category, Balance>
    fun getMonthlySummary(month: YearMonth): List<MonthSummary> {
        return TODO("Provide the return value")
    }
}