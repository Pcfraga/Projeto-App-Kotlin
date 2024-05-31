package com.example.aplicativobookstore.viewModel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.aplicativobookstore.data.Book


@Composable
fun AddBookScreen(navController: NavHostController, viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var coverImageUrl by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titulo") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = publisher,
            onValueChange = { publisher = it },
            label = { Text("Editora") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = coverImageUrl,
            onValueChange = { coverImageUrl = it },
            label = { Text("Cover Image URL") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.addBook(
                    Book(
                        title = title,
                        author = author,
                        publisher = publisher,
                        coverImageUrl = coverImageUrl
                    )
                )
                navController.navigateUp()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar livro")
        }
    }
}
