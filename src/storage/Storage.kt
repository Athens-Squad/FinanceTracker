package storage

import transactions.Transaction
import java.util.*

interface Storage {
    fun add(transaction: Transaction)
    fun edit(id: UUID, edited: Transaction): Boolean
    fun delete(id: UUID): Boolean
    fun getAll(): List<Transaction>
}