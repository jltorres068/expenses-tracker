package com.jl_torres.expensestracker.domain.usecase

import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.repository.IExpensesRepository
import javax.inject.Inject

class CreateExpenseUseCase @Inject constructor(
    private val repository: IExpensesRepository
) {
    suspend operator fun invoke(expense: Expense) {
        repository.createExpense(expense)
    }
}