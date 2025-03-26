package com.jl_torres.expensestracker.domain.usecase

import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.repository.IExpensesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: IExpensesRepository
) {
    suspend operator fun invoke(): Flow<List<Expense>> {
        return repository.getExpenses()
    }
}