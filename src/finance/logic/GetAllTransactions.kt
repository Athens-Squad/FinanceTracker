import finance.dataSource.TransactionDataSource
import finance.model.Transaction

class GetAllTransactions (
    private val dataSource: TransactionDataSource
) {
    fun getAllTransactions(): List<Transaction> {
        return TODO()
    }
}