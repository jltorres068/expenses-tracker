package com.jl_torres.expensestracker.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.Month

@Composable
fun ExpensesMonthFilter(
    modifier: Modifier = Modifier,
    monthSelected: Month,
    onSelectMonth: (month: Month) -> Unit,
) {
    var openDropdown by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .height(50.dp)
            .clickable { openDropdown = true }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 4.dp)
        ) {
            Text(
                text = monthSelected.toString().lowercase().replaceFirstChar { it.uppercase() },
            )

            Icon(
                imageVector = if(openDropdown) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Arrow Down",
                modifier = Modifier
                    .size(20.dp)
            )
        }

        DropdownMenu(
            expanded = openDropdown,
            onDismissRequest = {
                openDropdown = false
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
        ) {
            Month.entries.forEach { month ->
                Row(
                    modifier = Modifier
                        .background(if(monthSelected == month) MaterialTheme.colorScheme.inversePrimary else Color.Transparent)
                        .fillMaxWidth()
                        .clickable {
                            onSelectMonth(month)
                            openDropdown = false
                        }
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = month.toString().lowercase().replaceFirstChar { it.uppercase() }
                    )
                }
            }
        }
    }
}