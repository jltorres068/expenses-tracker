package com.jl_torres.expensestracker.domain.repository

import com.jl_torres.expensestracker.domain.model.Expense
import kotlinx.coroutines.flow.Flow

interface IExpensesRepository {
    suspend fun createExpense(expense: Expense)
    suspend fun getExpenses() : Flow<List<Expense>>
}