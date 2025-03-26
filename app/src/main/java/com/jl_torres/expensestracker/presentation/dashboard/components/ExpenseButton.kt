package com.jl_torres.expensestracker.presentation.dashboard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .padding(top = 10.dp, end = 10.dp)
    ) {
        Text(
            text = "+ Add Expense",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}