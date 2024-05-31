package com.example.aplicativobookstore.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Delete
    suspend fun deleteBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookByIdFlow(id: Int): Flow<Book?>

}
