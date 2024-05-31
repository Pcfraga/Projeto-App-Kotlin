package com.example.aplicativobookstore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplicativobookstore.data.BookDAO

class BookViewModelFactory(private val bookDAO: BookDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(bookDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

