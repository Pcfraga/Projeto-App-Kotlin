package com.example.aplicativobookstore.ui.theme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicativobookstore.data.AppDatabase
import com.example.aplicativobookstore.viewModel.BookViewModel
import com.example.aplicativobookstore.viewModel.BookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicativobookstoreApp()
        }
    }
}

@Composable
fun AplicativobookstoreApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val userDAO = AppDatabase.getDatabase(context).userDAO()
    val bookDAO = AppDatabase.getDatabase(context).bookDAO()
    val bookViewModelFactory = BookViewModelFactory(bookDAO = bookDAO)
    val bookViewModel: BookViewModel = viewModel(factory = bookViewModelFactory)

    AplicativobookstoreTheme {
        Surface(color = Color.White) {
            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController = navController, userDAO = userDAO)
                }
                composable("register") {
                    RegisterScreen(navController = navController, userDAO = userDAO)
                }
                composable("book_list") {
                    BookListScreen(
                        viewModel = bookViewModel,
                        navigateToAddBook = { navController.navigate("addBookScreen") },
                        navigateToEditBook = { bookId ->
                            navController.navigate("editBookScreen/$bookId")
                        }
                    )
                }
                composable("addBookScreen") {
                    AddBookScreen(navController = navController, viewModel = bookViewModel)
                }
                composable("editBookScreen/{bookId}") { backStackEntry ->
                    val bookId = backStackEntry.arguments?.getString("bookId")?.toInt() ?: return@composable
                    val book = bookViewModel.getBookById(bookId)
                    if (book != null) {
                        EditBookScreen(
                            book = book,
                            viewModel = bookViewModel,
                            onBookUpdated = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}