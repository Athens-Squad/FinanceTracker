package data

import finance.dataSource.FileTransactionDataSource
import finance.model.Transaction

class MockFileDataSourceImpl : FileTransactionDataSource {
    private val emptyMutableMap = mutableMapOf<String, Transaction>()

    override fun saveTransactionToFile(transaction: Transaction, isEdited: Boolean): Boolean {
        return false
    }

    override fun loadTransactionsFromFile(): MutableMap<String, Transaction> {
        return emptyMutableMap
    }

    override fun deleteTransactionFromFile(transactionId: String): Boolean {
        return false
    }
}
