package com.jl_torres.expensestracker.data.mapper

import com.jl_torres.expensestracker.data.local.entities.ExpenseEntity
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.model.ExpensesCategory
import java.time.Instant

fun ExpenseEntity.toExpense(): Expense {
    return Expense(
        id = id,
        amount = amount,
        category = ExpensesCategory.valueOf(category),
        createTime = Instant.ofEpochMilli(this.creationTime)
    )
}

fun Expense.toExpenseEntity(): ExpenseEntity {
    return ExpenseEntity(
        amount = amount,
        category = category.toString(),
        creationTime = createTime.toEpochMilli()
    )
}