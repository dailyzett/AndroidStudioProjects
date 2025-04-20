package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.network.BookVolume
import com.example.bookshelf.network.JazzHistory
import kotlinx.coroutines.launch


sealed interface BooksUiState {
    data class Success(val bookVolume: BookVolume) : BooksUiState
    object Loading : BooksUiState
}

class BookViewModel : ViewModel() {
    private val repository = BooksRepository()

    var uiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getJazzHistory()
    }

    private fun getJazzHistory() {
        viewModelScope.launch {
            val jazzHistory: JazzHistory = repository.getJazzHistory()
            val bookId = jazzHistory.items[0].id

            val bookVolumes: BookVolume = repository.getBookVolumes(bookId)

            uiState = BooksUiState.Success(bookVolumes)
        }
    }
}