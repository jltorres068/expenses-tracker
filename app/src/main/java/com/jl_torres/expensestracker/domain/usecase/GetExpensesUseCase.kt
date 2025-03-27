package com.jl_torres.expensestracker.domain.usecase

import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.repository.IExpensesRepository
import com.jl_torres.expensestracker.domain.util.getInitialMonthDate
import com.jl_torres.expensestracker.domain.util.getLastMonthDate
import kotlinx.coroutines.flow.Flow
import java.time.Month
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: IExpensesRepository
) {
    suspend operator fun invoke(month: Month): Flow<List<Expense>> {
        return repository.getExpenses(
            startTime = month.getInitialMonthDate(),
            endTime = month.getLastMonthDate()
        )
    }
}