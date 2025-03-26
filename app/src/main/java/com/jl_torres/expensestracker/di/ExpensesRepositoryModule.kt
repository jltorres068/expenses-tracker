package com.jl_torres.expensestracker.di

import com.jl_torres.expensestracker.data.local.dao.ExpensesDao
import com.jl_torres.expensestracker.data.repository.ExpensesRepositoryImpl
import com.jl_torres.expensestracker.domain.repository.IExpensesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExpensesRepositoryModule {
    @Provides
    @Singleton
    fun providesExpenseRepository(dao: ExpensesDao): IExpensesRepository {
        return ExpensesRepositoryImpl(dao)
    }
}