package com.jl_torres.expensestracker.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jl_torres.expensestracker.R
import com.jl_torres.expensestracker.presentation.dashboard.components.ExpenseChart
import com.jl_torres.expensestracker.presentation.dashboard.components.ExpensesSummary
import com.jl_torres.expensestracker.presentation.shared.ExpensesMonthFilter

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onAddExpense: () -> Unit,
    onDetails: () -> Unit,
) {
    val expenses by viewModel.expenses.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val monthFilter by viewModel.monthSelected.collectAsState()

    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
            .padding(10.dp)
    ) {
        //region Header
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.expenses_tracker),
                    fontSize = MaterialTheme.typography.displayMedium.fontSize,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold,
                    lineHeight = MaterialTheme.typography.displayMedium.lineHeight,
                    modifier = Modifier
                        .weight(1f)
                )

                Button(
                    onClick = { onAddExpense() },
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.add_expense),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        //endregion

        //region Expenses description
        item {
            Spacer(Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.expenses_description),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
            )
            Spacer(Modifier.height(20.dp))
        }
        //endregion

        //region Month filter
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.expenses_month)
                )

                ExpensesMonthFilter(
                    modifier = Modifier
                        .width(150.dp),
                    monthSelected = monthFilter,
                    onSelectMonth = { viewModel.onMonthChange(it) }
                )
            }
        }
        //endregion

        //region Summary/loading
        item {
            if(loading) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(40.dp)
                    )
                }
            } else {
                if(expenses.isNotEmpty()) {
                    Spacer(Modifier.height(10.dp))

                    ExpenseChart(expenses)

                    Spacer(Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            onClick = { onDetails() },
                        ) {
                            Text(
                                text = stringResource(R.string.details),
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    ExpensesSummary(
                        expenses = expenses
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp)
                    ){
                        Text(
                            text = stringResource(R.string.no_expenses_yet),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            textAlign = TextAlign.Center,
                        )
                    }

                }
            }
        }
        //endregion
    }
}