package com.jl_torres.expensestracker.presentation.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.model.ExpensesCategory
import com.jl_torres.expensestracker.presentation.util.getColor
import com.jl_torres.expensestracker.presentation.util.toPx

@Composable
fun ExpenseChart(expenses: List<Expense>) {
    val categorySums = getCategorySums(expenses)

    val chartHeight = 200.dp
    val barWidth = 40.dp

    val categoryColors = mapOf(
        ExpensesCategory.FOOD to ExpensesCategory.FOOD.getColor(),
        ExpensesCategory.SERVICES to ExpensesCategory.SERVICES.getColor(),
        ExpensesCategory.ENTERTAINMENT to ExpensesCategory.ENTERTAINMENT.getColor(),
        ExpensesCategory.HOME_LOAN to ExpensesCategory.HOME_LOAN.getColor(),
        ExpensesCategory.STREAMING to ExpensesCategory.STREAMING.getColor(),
        ExpensesCategory.PERSONAL_LOAN to ExpensesCategory.PERSONAL_LOAN.getColor(),
        ExpensesCategory.STATIONARY to ExpensesCategory.STATIONARY.getColor(),
    )

    val maxAmount = categorySums.values.maxOrNull() ?: 0.0
    val scaleFactor = chartHeight.toPx() / maxAmount

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        Canvas(
            modifier = Modifier
                .height(chartHeight)
        ) {
            var xPosition = 0f
            val spaceBetweenBars = 16.dp.toPx()

            categorySums.forEach { (category, sum) ->
                val barHeight = (sum * scaleFactor).toFloat()

                drawRect(
                    color = categoryColors[category] ?: Color.Black,
                    size = androidx.compose.ui.geometry.Size(barWidth.toPx(), barHeight),
                    topLeft = androidx.compose.ui.geometry.Offset(xPosition, chartHeight.toPx() - barHeight)
                )

                xPosition += barWidth.toPx() + spaceBetweenBars
            }
        }
    }
}

fun getCategorySums(expenses: List<Expense>): Map<ExpensesCategory, Double> {
    return expenses.groupBy { it.category }
        .mapValues { entry -> entry.value.sumOf { it.amount } }
}