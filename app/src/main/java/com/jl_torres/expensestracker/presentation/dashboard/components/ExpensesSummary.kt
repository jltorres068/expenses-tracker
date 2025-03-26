package com.jl_torres.expensestracker.presentation.dashboard.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.domain.model.ExpensesCategory

@Composable
fun ExpensesSummary(
    modifier: Modifier = Modifier,
    expenses: List<Expense>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Summary",
        )

        ExpensesCategory.entries.forEach { category ->
            Text(
                text = buildAnnotatedString {
                    append(category.description)
                    withStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    ) {
                        append(expenses.count { it.category == category }.toString())
                    }
                },
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            )
        }
    }
}