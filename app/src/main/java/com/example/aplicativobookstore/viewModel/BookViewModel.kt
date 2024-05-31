package com.example.aplicativobookstore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicativobookstore.data.Book
import com.example.aplicativobookstore.data.BookDAO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val bookDAO: BookDAO) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> get() = _books

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            _books.value = bookDAO.getAllBooks()
        }
    }

    fun getBookById(bookId: Int): Book? {
        return _books.value.find { it.id == bookId }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            bookDAO.insertBook(book)
            loadBooks()
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            bookDAO.updateBook(book)
            loadBooks()
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookDAO.deleteBook(book)
            loadBooks()
        }
    }
}
