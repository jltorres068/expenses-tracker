package com.jl_torres.expensestracker.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jl_torres.expensestracker.presentation.dashboard.DashboardScreen
import com.jl_torres.expensestracker.presentation.expenseform.ExpenseFormScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
            .padding(paddingValues)
    ) {
        NavHost(navController, startDestination = "dashboard") {
            composable("dashboard") {
                DashboardScreen(
                    onAddExpense = { navController.navigate("expenseform") }
                )
            }
            composable("expenseform") {
                ExpenseFormScreen(
                    snackBarState = snackbarHostState,
                    onSuccess = { navController.popBackStack() }
                )
            }
        }
    }
}