package com.example.aplicativobookstore.ui.theme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicativobookstore.data.User
import com.example.aplicativobookstore.data.UserDAO
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, userDAO: UserDAO) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color.Blue, Color.Gray, Color.Black),
        startY = 0f,
        endY = 1500f
    )
    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "BOOK STORE",
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirme sua senha") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                            if (password == confirmPassword) {
                                val newUser = User(username = username, password = password)
                                userDAO.insertUser(newUser)
                                navController.navigate("login")
                            } else {
                                errorMessage = "Senhas não conferem"
                            }
                        } else {
                            errorMessage = "Por favor, preencha todos os campos"
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registre-se", color = Color.White)
            }
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Já tem uma conta? Conecte-se", color = Color.White)
            }
        }
    }
}
