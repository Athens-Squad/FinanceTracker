package finance.dataSource

import finance.model.Transaction

interface FileTransactionDataSource {

    fun saveTransactionToFile(transaction: Transaction, isEdited: Boolean = false): Boolean

    fun loadTransactionsFromFile(): MutableMap<String, Transaction>

    fun deleteTransactionFromFile(transactionId: String): Boolean
}