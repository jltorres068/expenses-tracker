package com.jl_torres.expensestracker.presentation.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.usecase.GetExpensesUseCase
import com.jl_torres.expensestracker.presentation.util.getCurrentMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Month
import javax.inject.Inject

@HiltViewModel
class ExpensesTableViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    private val _monthSelected = MutableStateFlow(getCurrentMonth())
    val monthSelected: StateFlow<Month> = _monthSelected

    init {
        fetchExpenses()
    }

    fun onMonthChange(value: Month) {
        _monthSelected.value = value
        fetchExpenses()
    }

    private fun fetchExpenses() {
        viewModelScope.launch {
            try {
                getExpensesUseCase.invoke(_monthSelected.value).collect { expenseRows ->
                    _expenses.value = expenseRows
                }
            } catch (_: Exception) { }
        }
    }
}