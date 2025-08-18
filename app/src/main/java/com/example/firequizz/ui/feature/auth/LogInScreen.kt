package com.example.todolist2.ui.feature

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todolist2.navigation.ListRoute
import com.example.todolist2.ui.feature.auth.AuthState
import com.example.todolist2.ui.feature.auth.AuthViewModel
import com.example.todolist2.ui.theme.Gray
import com.example.todolist2.ui.theme.LightGray
import com.example.todolist2.ui.theme.TodoList2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Autheticated -> {
                navController.navigate(ListRoute)
            }

            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "To-Do List", fontWeight = FontWeight.SemiBold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Gray,
                    titleContentColor = LightGray,
                )
            )
        },
    ) { paddingValues ->


        Column(
            modifier = modifier.fillMaxSize()
                .consumeWindowInsets(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(value = email, onValueChange = {
                email = it
            }, label = {
                Text(text = "E-mail")
            })

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = password, onValueChange = {
                password = it
            }, label = {
                Text(text = "Password")
            })

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                authViewModel.login(email, password)
            }, enabled = authState.value != AuthState.Loading) {
                Text(text = "Log In")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                navController.navigate("signup")
            }) {
                Text(text ="Don't have an account?")
            }
        }
    }
}

@Preview
@Composable
private fun LogInPreview() {
    TodoList2Theme {
        LogInScreen(
            modifier = Modifier,
            navController = rememberNavController(),
            authViewModel = AuthViewModel()
        )
    }

}