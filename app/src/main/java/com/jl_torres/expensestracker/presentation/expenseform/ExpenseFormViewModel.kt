package com.jl_torres.expensestracker.presentation.expenseform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.model.ExpensesCategory
import com.jl_torres.expensestracker.domain.usecase.CreateExpenseUseCase
import com.jl_torres.expensestracker.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseFormViewModel @Inject constructor(
    private val createExpenseUseCase: CreateExpenseUseCase
) : ViewModel() {
    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount

    private val _expenseCategory = MutableStateFlow<ExpensesCategory?>(null)
    val expenseCategory: StateFlow<ExpensesCategory?> = _expenseCategory

    private val _allowSave = MutableStateFlow(false)
    val allowSave: StateFlow<Boolean> = _allowSave

    private val _saving = MutableStateFlow(false)
    val saving: StateFlow<Boolean> = _saving

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            combine(_amount, _expenseCategory) { amount, category ->
                amount.toDoubleOrNull() != null && category != null
            }.collect { isValid ->
                _allowSave.value = isValid
            }
        }
    }

    fun onExpenseChange(value: String) {
        if(value.toDoubleOrNull() != null) {
            _amount.value = value
        } else if(value.isBlank()) {
            _amount.value = ""
        }
    }

    fun onSelectCategory(category: ExpensesCategory?) {
        _expenseCategory.value = category
    }

    fun onCreateExpense() {
        viewModelScope.launch {
            if(validExpense()) {
                try {
                    _saving.value = true
                    createExpenseUseCase(
                        Expense(
                            amount = _amount.value.toDouble(),
                            category = _expenseCategory.value!!
                        )
                    )
                    _uiEvent.send(UiEvent.Success)
                    _uiEvent.send(UiEvent.ShowSnackbar("Expense created successfully"))
                } finally {
                    _saving.value = false
                }
            } else {
                _uiEvent.send(UiEvent.ShowSnackbar("Something went wrong. Please, try againn"))
            }
        }
    }

    private fun validExpense() : Boolean {
        return _amount.value.toDoubleOrNull() != null && _expenseCategory.value != null
    }
}