package com.example.firequizz.ui.feature

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.firequizz.R
import com.example.firequizz.ui.feature.auth.AuthState
import com.example.firequizz.ui.feature.auth.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit,
    onLogIn: () -> Unit
) {
    var username by remember {
        mutableStateOf("")
    }

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
                onLoginSuccess()
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
                    containerColor = Color.Gray,
                    titleContentColor = colorResource(R.color.grey),
                )
            )
        },
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(value = username, onValueChange = {
                username = it
            }, label = {
                Text(text = "Username")
            })

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
                authViewModel.signup(username, email, password)
            }, enabled = authState.value != AuthState.Loading) {
                Text(text = "Sign Up")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = {
                onLogIn()
            }) {
                Text(text ="Already have an account?")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        authViewModel = AuthViewModel(),
        onLoginSuccess = {},
        onLogIn = {}
    )
}