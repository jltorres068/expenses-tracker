package com.jl_torres.expensestracker.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.jl_torres.expensestracker.domain.model.ExpensesCategory

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { this@toPx.toPx() }

fun ExpensesCategory.getColor(): Color {
    return when(this) {
        ExpensesCategory.FOOD -> Color.Cyan
        ExpensesCategory.SERVICES -> Color.Blue
        ExpensesCategory.HOME_LOAN -> Color.Red
        ExpensesCategory.STREAMING -> Color.Yellow
        ExpensesCategory.PERSONAL_LOAN -> Color.Green
        ExpensesCategory.STATIONARY -> Color.Black
        ExpensesCategory.ENTERTAINMENT -> Color.Magenta
    }
}