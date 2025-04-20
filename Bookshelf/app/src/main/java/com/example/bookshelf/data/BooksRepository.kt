package com.example.bookshelf.data

import com.example.bookshelf.network.BookVolume
import com.example.bookshelf.network.JazzHistory
import com.example.bookshelf.network.RetrofitClient

class BooksRepository {
    private val apiService = RetrofitClient.booksApiService

    suspend fun getJazzHistory(): JazzHistory {
        return apiService.getJazzHistory()
    }

    suspend fun getBookVolumes(bookId: String): BookVolume {
        return apiService.getBookVolumes(bookId)
    }
}