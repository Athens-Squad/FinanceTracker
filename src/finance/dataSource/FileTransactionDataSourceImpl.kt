package finance.dataSource

import finance.model.Transaction
import java.io.File
import java.time.format.DateTimeFormatter
import kotlin.io.writeText as writeText

class FileTransactionDataSourceImpl(
    private val filePath: String = "transactions.txt",
    private val transactionParser: TransactionParser) : TransactionDataSource {
    // Date formatter for converting between LocalDate and String
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private var transactions: MutableMap<String, Transaction> = mutableMapOf()

    init {
        // Load transactions from file when data source is created
        loadTransactionsFromFile()
    }


    override fun addTransaction(transaction: Transaction): Boolean {
        transactions[transaction.id] = transaction
        return saveTransactionToFile(transaction)
    }

    override fun editTransaction(editedTransaction: Transaction): Boolean {
        return if (transactions.containsKey(editedTransaction.id)) {
            transactions[editedTransaction.id] = editedTransaction
            saveTransactionToFile(editedTransaction, isEdited = true)
            true
        } else {
            false
        }
    }

    override fun deleteTransaction(id: String): Boolean {
        return if (transactions.containsKey(id)) {
            transactions.remove(id)
            deleteTransactionFromFile(id)
            true
        } else {
            false
        }
    }


    override fun getAllTransactions(): List<Transaction> = transactions.values.toList()

    override fun getTransactionById(id: String): Transaction? = transactions[id]

    // Load transactions from the file
    private fun loadTransactionsFromFile(): MutableMap<String, Transaction> {
        val file = File(filePath)
        val tempTransactions = mutableMapOf<String, Transaction>()

        if (!file.exists()) {
            return tempTransactions// No file to load from yet
        }

        try {
            tempTransactions.clear()

            file.readLines().forEach { line ->
                if (line.isNotBlank()) {
                    val transaction = transactionParser.parseLine(line)
                    if (transaction != null) {
                        tempTransactions[transaction.id] = transaction
                    }
                }

            }
        } catch (e: Exception) {
            println("Error loading transactions: ${e.message}")
            // If loading fails, start with empty transactions
            tempTransactions.clear()
        }
        return tempTransactions
    }

    private fun deleteTransactionFromFile(transactionId: String): Boolean {
        val file = File(filePath)

        if (!file.exists()) {
            return false// No file to load from yet
        }

        return try {
            val lines = file.readLines().toMutableList()
            for (lineIndex in lines.indices) {
                val line = lines[lineIndex]
                if (line.isNotBlank()) {
                    val transaction = transactionParser.parseLine(line)
                    if (transaction != null) {
                        if (transaction.id == transactionId) {
                            lines.removeAt(lineIndex)
                            break
                        }
                    }

                }
            }
            file.writeText(lines.joinToString("\n"))
            true
        } catch (e: Exception) {
            println("Error loading transactions: ${e.message}")
            false
        }
    }

    // Save transaction to the file
    private fun saveTransactionToFile(transaction: Transaction, isEdited: Boolean = false): Boolean {
        return try {
            val file = File(filePath)

            val transactionLine = transactionParser.parseTransactionToLine(transaction)

            // id is the common
            if (isEdited) {
                deleteTransactionFromFile(transaction.id)
            }
            file.appendText("\n$transactionLine")

            true
        } catch (e: Exception) {
            println("Error saving transactions: ${e.message}")
            false
        }
    }

}