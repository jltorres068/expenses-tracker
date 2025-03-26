package com.jl_torres.expensestracker.presentation.table.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jl_torres.expensestracker.domain.model.Expense
import com.jl_torres.expensestracker.presentation.util.formatDateDayMonthYear

@Composable
fun ExpensesTable(
    modifier: Modifier = Modifier,
    expenses: List<Expense>
) {
    Column(
        modifier = modifier
    ) {
        // Encabezado de la tabla
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Amount",
                modifier = Modifier
                    .weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Category",
                modifier = Modifier
                    .weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "Date",
                modifier = Modifier.weight(1f),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        // Cuerpo de la tabla
        LazyColumn {
            items(expenses) { expense ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$${expense.amount}",
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = expense.category.description,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Text(
                        text = expense.createTime.formatDateDayMonthYear(),
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}