package com.jl_torres.expensestracker.presentation.util

sealed class UiEvent {
    data object Success: UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}