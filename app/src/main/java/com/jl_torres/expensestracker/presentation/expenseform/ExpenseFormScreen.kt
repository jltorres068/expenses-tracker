package com.jl_torres.expensestracker.presentation.expenseform

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jl_torres.expensestracker.domain.model.ExpensesCategory
import com.jl_torres.expensestracker.presentation.util.UiEvent

@Composable
fun ExpenseFormScreen(
    snackBarState: SnackbarHostState,
    viewModel: ExpenseFormViewModel = hiltViewModel(),
    onSuccess: () -> Unit,
) {
    val amount by viewModel.amount.collectAsState()
    val allowSave by viewModel.allowSave.collectAsState()
    val categorySelected by viewModel.expenseCategory.collectAsState()
    var openDropdown by remember { mutableStateOf(false) }
    val isSaving by viewModel.saving.collectAsState()

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onSuccess()
                is UiEvent.ShowSnackbar -> {
                    snackBarState.showSnackbar(event.message)
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            text = "Add your expenses quickly and stay on top of your spending with clear insights.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(300.dp)
        )

        Spacer(Modifier.height(40.dp))

        TextField(
            value = amount,
            colors = TextFieldDefaults.colors(
                focusedContainerColor =  MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { viewModel.onExpenseChange(it) },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .height(50.dp)
                .width(250.dp),
            placeholder = {
                Text(
                    text = "0.00",
                )
            }
        )

        Spacer(Modifier.height(20.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .height(50.dp)
                .clickable { openDropdown = true }
                .width(250.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Text(
                    text = categorySelected?.description ?: "Select expense category",
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
            ) {
                ExpensesCategory.entries.forEach {
                    Row(
                        modifier = Modifier
                            .background(if(categorySelected == it) MaterialTheme.colorScheme.inversePrimary else Color.Transparent)
                            .width(250.dp)
                            .clickable {
                                viewModel.onSelectCategory(it)
                                openDropdown = false
                            }
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = it.description
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        if(isSaving) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(20.dp)
                )
            }
        } else {
            Button(
                onClick = { viewModel.onCreateExpense() },
                enabled = allowSave,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            ) {
                Text(
                    text = "Save"
                )
            }
        }
    }
}