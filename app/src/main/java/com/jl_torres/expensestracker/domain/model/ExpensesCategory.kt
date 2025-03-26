package com.jl_torres.expensestracker.domain.model

enum class ExpensesCategory(val description: String) {
    ENTERTAINMENT("Entertainment"),
    FOOD("Food"),
    STREAMING("Streaming"),
    SERVICES("Services"),
    STATIONARY("Stationary"),
    HOME_LOAN("Home Loan"),
    PERSONAL_LOAN("Personal Loan")
}