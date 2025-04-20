package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansModel
import com.example.amphibians.data.AmphibiansRepository
import kotlinx.coroutines.launch

sealed interface AmphibiansUiState {
    data class Success(val amphibianList: List<AmphibiansModel>) : AmphibiansUiState
    object Loading : AmphibiansUiState
}

class AmphibiansViewModel(
    private val amphibianRepository: AmphibiansRepository
) : ViewModel() {

    var uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            uiState = AmphibiansUiState.Success(amphibianRepository.getData())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibianRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}