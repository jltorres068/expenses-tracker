package com.jl_torres.expensestracker.di

import android.content.Context
import androidx.room.Room
import com.jl_torres.expensestracker.data.local.dao.ExpensesDao
import com.jl_torres.expensestracker.data.local.database.ExpenseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): ExpenseDatabase {
        return Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "expense_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExpensesDao(database: ExpenseDatabase): ExpensesDao {
        return database.expenseDao()
    }
}