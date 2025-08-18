package com.example.todolist2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.todolist2.ui.feature.LogInScreen
import com.example.todolist2.ui.feature.SignUpScreen
import com.example.todolist2.ui.feature.addedit.AddEditScreen
import com.example.todolist2.ui.feature.auth.AuthViewModel
import com.example.todolist2.ui.feature.list.ListScreen
import kotlinx.serialization.Serializable
import java.nio.file.WatchEvent

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun TodoNavHost(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login"){
            LogInScreen(modifier = Modifier, navController, authViewModel)
        }

        composable("signup"){
            SignUpScreen(modifier = Modifier, navController, authViewModel)
        }

        composable<ListRoute> {
            ListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable<AddEditRoute> { backStackEntry ->
            val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            AddEditScreen(
                id = addEditRoute.id,
                navigateBack = {
                    navController.popBackStack()
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}