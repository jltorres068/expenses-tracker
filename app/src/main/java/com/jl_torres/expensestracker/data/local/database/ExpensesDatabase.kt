package com.jl_torres.expensestracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jl_torres.expensestracker.data.local.dao.ExpensesDao
import com.jl_torres.expensestracker.data.local.entities.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpensesDao
}