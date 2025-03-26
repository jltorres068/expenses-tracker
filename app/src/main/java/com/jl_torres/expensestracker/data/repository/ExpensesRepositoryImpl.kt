package com.jl_torres.expensestracker.data.repository

import com.jl_torres.expensestracker.data.local.dao.ExpensesDao
import com.jl_torres.expensestracker.data.mapper.toExpense
import com.jl_torres.expensestracker.data.mapper.toExpenseEntity
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.repository.IExpensesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpensesRepositoryImpl @Inject constructor(
    private val dao: ExpensesDao
) : IExpensesRepository {
    override suspend fun createExpense(expense: Expense) {
        dao.createExpense(expense.toExpenseEntity())
    }

    override suspend fun getExpenses(): Flow<List<Expense>> {
        return dao.getAllExpenses().map { list ->
            list.map { entity ->
                entity.toExpense()
            }
        }
    }
}