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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jl_torres.expensestracker.presentation.dashboard.components.DashboardHeader
import com.jl_torres.expensestracker.presentation.dashboard.components.ExpenseButton
import com.jl_torres.expensestracker.presentation.dashboard.components.ExpensesSummary

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onAddExpense: () -> Unit,
    onDetails: () -> Unit,
) {
    val expenses by viewModel.expenses.collectAsState()
    val loading by viewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DashboardHeader(
                modifier = Modifier
                    .weight(3f)
            )

            ExpenseButton(
                modifier = Modifier
                    .align(Alignment.Top),
                onClick = { onAddExpense() }
            )
        }

        Spacer(Modifier.height(50.dp))

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
            ExpensesSummary(
                expenses = expenses
            )

            Spacer(Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { onDetails() },
                ) {
                    Text(
                        text = "Details",
                    )
                }
            }
        }
    }
}