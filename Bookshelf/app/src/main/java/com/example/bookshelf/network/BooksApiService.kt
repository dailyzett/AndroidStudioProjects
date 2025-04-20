package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {
    @GET("?q=jazz")
    suspend fun getJazzHistory(): JazzHistory

    @GET("{bookId}")
    suspend fun getBookVolumes(@Path("bookId") bookId: String): BookVolume
}