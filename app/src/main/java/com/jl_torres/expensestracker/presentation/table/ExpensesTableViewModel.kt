package com.jl_torres.expensestracker.presentation.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.usecase.GetExpensesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesTableViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase
) : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    init {
        fetchExpenses()
    }

    private fun fetchExpenses() {
        viewModelScope.launch {
            try {
                getExpensesUseCase.invoke().collect { expenseRows ->
                    _expenses.value = expenseRows
                }
            } catch (_: Exception) { }
        }
    }
}