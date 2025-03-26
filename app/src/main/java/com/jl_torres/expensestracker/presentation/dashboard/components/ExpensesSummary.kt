package com.jl_torres.expensestracker.presentation.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.model.ExpensesCategory

@Composable
fun ExpensesSummary(
    modifier: Modifier = Modifier,
    expenses: List<Expense>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        Text(
            text = "Total amounts per Category",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExpensesCategory.entries.forEach { category ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = "$${expenses.filter{ it.category == category }.sumOf{ it.amount }}",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(3f)
                    ) {
                        Text(
                            text = category.description,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                        )
                    }
                }
            }
        }
    }
}

// Función para obtener un color según la categoría
private fun getCategoryColor(label: String): Color {
    return when (label) {
        "Category 1" -> Color.Red
        "Category 2" -> Color.Green
        "Category 3" -> Color.Blue
        "Category 4" -> Color.Yellow
        "Category 5" -> Color.Cyan
        else -> Color.Gray
    }
}

@Composable
fun DemoMultiCategoryChart() {
    val categories = listOf(
        "Category 1" to 40f,
        "Category 2" to 20f,
        "Category 3" to 15f,
        "Category 4" to 10f,
        "Category 5" to 15f
    )

    MultiCategoryCircularChart(
        categories = categories,
        modifier = Modifier.size(120.dp)
    )
}

@Composable
fun MultiCategoryCircularChart(categories: List<Pair<String, Float>>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val size = size.minDimension
        val strokeWidth = 40f
        val center = Offset(size / 2, size / 2)
        val radius = size / 2 - strokeWidth / 2

        // Cálculo del total para normalizar los valores
        val total = categories.map { it.second }.sum()
        var startAngle = -90f  // Empezamos desde la parte superior

        categories.forEach { (label, value) ->
            val sweepAngle = (value / total) * 360f

            // Dibujar cada segmento del gráfico circular
            drawArc(
                color = getCategoryColor(label), // Asigna un color según la categoría
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth)
            )

            // Actualizar el ángulo de inicio para el siguiente segmento
            startAngle += sweepAngle
        }
    }
}

fun ExpensesCategory.getColor(): Color {
    return when(this) {
        ExpensesCategory.FOOD -> Color.Cyan
        ExpensesCategory.SERVICES -> Color.Blue
        ExpensesCategory.HOME_LOAN -> Color.Red
        ExpensesCategory.STREAMING -> Color.Green
        ExpensesCategory.ENTERTAINMENT -> Color.Yellow
        ExpensesCategory.PERSONAL_LOAN -> Color.LightGray
        ExpensesCategory.STATIONARY -> Color.Black
    }
}

fun calculateCategoryPercentages(expenses: List<Expense>): List<Pair<String, Float>> {
    val total = expenses.sumOf { it.amount }
    return expenses
        .groupBy { it.category.description }
        .map { (category, expenses) ->
            category to ((expenses.sumOf { it.amount } / total) * 100f).toFloat()
        }
}