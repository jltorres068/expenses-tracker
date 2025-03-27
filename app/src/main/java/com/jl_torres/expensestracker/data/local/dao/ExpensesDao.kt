package com.jl_torres.expensestracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jl_torres.expensestracker.data.local.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses WHERE creationTime BETWEEN :startTime AND :endTime")
    fun getAllExpenses(startTime: Long, endTime: Long): Flow<List<ExpenseEntity>>
}