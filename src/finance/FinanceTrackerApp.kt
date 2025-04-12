package finance

import finance.dataSource.TransactionDataSourceImp
import finance.logic.TransactionManagerImpl
import finance.model.Transaction
import finance.model.TransactionType
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeParseException
import java.util.UUID

class FinanceTrackerApp {
    private val transactionManager = TransactionManagerImpl(TransactionDataSourceImp())

    fun start() {
        println("Welcome to Personal Finance Tracker\n")
        while (true) {
            showMenu()
            when (readlnOrNull()) {
                "1" -> addTransaction()
                "2" -> viewAllTransactions()
                "3" -> viewMonthlySummary()
                "4" -> editTransaction()
                "5" -> deleteTransaction()
                "0" -> {
                    exitApp()
                    break
                }
            }
        }
    }
    private fun showMenu() {
        println(
            """
            |===============================
            |Choose an option:
            |1. ➕ Add Transaction
            |2. 📄 View All Transactions
            |3. 📊 View Monthly Summary
            |4. ✏️ Edit Transaction
            |5. 🗑️ Delete Transaction
            |0. 🚪 Exit
            |===============================
            """.trimMargin()
        )
    }

    private fun addTransaction() {
        val transaction = getTransactionDetails() ?: return
        transactionManager.addTransaction(transaction)
        println("✅ Transaction added: ${transaction.id} | ${transaction.transactionType} | ${transaction.category} | ${transaction.amount} | ${transaction.date}\n")
    }

    private fun viewAllTransactions(){
        val transactions = transactionManager.getAllTransactions()
        if (transactions.isEmpty()) {
            println("❌ No transactions found.")
        } else {
            println("📜 All Transactions:")
            transactions.forEach { transaction ->
                println("ID: ${transaction.id} | ${transaction.transactionType} | ${transaction.category} | ${transaction.amount} | ${transaction.date}")
            }
        }
    }
    private fun viewMonthlySummary(){
        val yearMonth = promptForYearMonth("📅 Enter month for summary (yyyy-MM)") ?: return
        val summary = transactionManager.getSummaryMonth(yearMonth)
        println(
            """
            📊 Monthly Summary for $yearMonth
            -------------------------------
            Total Income  : ${summary.income}
            Total Expense : ${summary.expense}
            💰 Balance    : ${summary.balance}
            -------------------------------
            Transactions:
            """.trimIndent()
        )

        if (summary.transactions.isEmpty()) {
            println("⚠️ No transactions found for this month.")
        } else {
            summary.transactions.forEach {
                println("🔸 ${it.id} | ${it.transactionType} | ${it.category} | ${it.amount} | ${it.date}")
            }
        }
        println()
    }

    private fun editTransaction() {
        while (true) {
            println("Enter the ID of the transaction to edit (or type 'exit' to cancel):")
            val inputId = readlnOrNull().orEmpty()
            if (inputId.equals("exit", ignoreCase = true)) {
                println("❌ Editing canceled.")
                return
            }
            val id = try {
                UUID.fromString(inputId).toString()
            } catch (e: IllegalArgumentException) {
                println("❌ Invalid ID format. Please enter a valid UUID.")
                continue
            }
            val transaction = transactionManager.getTransactionById(id)
            if (transaction == null) {
                println("❌ Transaction with ID $id not found.")
                continue
            }

            val newAmount = promptForAmount("Enter new amount (or type 'exit' to cancel)") ?: return
            val newType =
                promptForTransactionType("Enter new type (income/expense) (or type 'exit' to cancel)") ?: return
            val newCategory = promptForInput("Enter new category (or type 'exit' to cancel)") ?: return
            val newDate = promptForDate("Enter new date (yyyy-MM-dd) (or type 'exit' to cancel)") ?: return

            if (newType == null || newCategory.isBlank() || newDate == null) {
                println("❌ Editing canceled.")
                return
            }

            val updated = transaction.copy(
                amount = newAmount,
                transactionType = newType,
                category = newCategory,
                date = newDate
            )

            transactionManager.editTransaction(updated)
            println("✅ Transaction updated: ${updated.id} | ${updated.transactionType} | ${updated.category} | ${updated.amount} | ${updated.date}\n")
            return
        }
    }
    private fun deleteTransaction(){
        println("🗑️ Enter the ID of the transaction to delete:")
        val inputId = readlnOrNull().orEmpty()
        val id = try {
            UUID.fromString(inputId).toString()
        } catch (e: IllegalArgumentException) {
            println("❌ Invalid ID format.")
            return
        }

        val transaction = transactionManager.getTransactionById(id)
        if (transaction == null) {
            println("❌ Transaction not found.")
            return
        }

        transactionManager.deleteTransaction(id)
        println("✅ Transaction deleted successfully.\n")
    }
    private fun exitApp() {
        println("\uD83D\uDC4B Exiting Finance Tracker. Goodbye!")
    }
    private fun getTransactionDetails(): Transaction? {
        val amount = promptForAmount("Enter amount") ?: return null
        val type = promptForTransactionType("Enter type (income/expense)") ?: return null
        val category = promptForInput("Enter category")
        val date = promptForDate("Enter date (yyyy-MM-dd)") ?: return null

        return Transaction(
            amount = amount,
            transactionType = type,
            category = category,
            date = date
        )
    }

    private fun promptForInput(prompt: String, defaultValue: String = ""): String {
        print("$prompt: ")
        val input = readlnOrNull()?.ifBlank { defaultValue }
        return input.orEmpty()
    }

    private fun promptForAmount(prompt: String): Int? {
        print("$prompt: ")
        val input = readlnOrNull()?.trim()
        if (input.equals("exit", ignoreCase = true)) return null
        val amount = input?.toIntOrNull()
        if (amount == null) {
              println("❌ Invalid amount. Please enter a valid number.")
       }
       return amount
    }

    private fun promptForTransactionType(prompt: String): TransactionType? {
        print("$prompt: ")
        return when (readlnOrNull()?.lowercase()) {
            "income" -> TransactionType.INCOME
            "expense" -> TransactionType.EXPENSE
            else -> {
                println("❌ Invalid type. Please enter 'income' or 'expense'.")
                null
            }
        }
    }

    private fun promptForDate(prompt: String): LocalDate? {
        while (true) {
            print("$prompt: ")
            val input = readlnOrNull().orEmpty()
            if (input.isBlank()) return null
            try {
                return LocalDate.parse(input)
            } catch (e: DateTimeParseException) {
                println("❌ Invalid date format. Please use 'yyyy-MM-dd'.")
            }
        }

    }

    private fun promptForYearMonth(prompt: String): YearMonth? {
        while (true) {
            print("$prompt: ")
            val input = readlnOrNull()?.trim().orEmpty()
            if (input.isBlank()) return null

            return try {
                YearMonth.parse(input)
            } catch (e: Exception) {
                println("❌ Invalid format. Please use 'yyyy-MM'. Try again.")
                continue
            }
        }
    }

}