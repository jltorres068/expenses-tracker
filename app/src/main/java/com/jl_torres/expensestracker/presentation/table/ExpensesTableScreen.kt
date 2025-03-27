package com.jl_torres.expensestracker.presentation.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jl_torres.expensestracker.R
import com.jl_torres.expensestracker.presentation.shared.ExpensesMonthFilter
import com.jl_torres.expensestracker.presentation.table.components.ExpensesTable

@Composable
fun ExpensesTableScreen(
    viewModel: ExpensesTableViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val expenses by viewModel.expenses.collectAsState()
    val monthFilter by viewModel.monthSelected.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        ExpensesTable(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 130.dp),
            expenses
        )

        Text(
            text = stringResource(R.string.details),
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold,
            lineHeight = MaterialTheme.typography.displayMedium.lineHeight,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 10.dp, start = 10.dp)
        )

        ExpensesMonthFilter(
            modifier = Modifier
                .width(150.dp)
                .align(Alignment.TopStart)
                .padding(top = 70.dp, start = 10.dp),
            monthSelected = monthFilter,
            onSelectMonth = { viewModel.onMonthChange(it) }
        )

        Button(
            onClick = { onBack() },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.inverseSurface,
                disabledContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 10.dp, end = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.back),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}