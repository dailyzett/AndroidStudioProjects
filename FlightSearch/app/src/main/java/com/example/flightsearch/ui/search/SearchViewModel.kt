package com.example.flightsearch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite
import com.example.flightsearch.data.FlightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: FlightRepository,
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    private val _code = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    //추천 결과
    val suggestions: StateFlow<List<Airport>> = _keyword
        .debounce(300)
        .filter { it.isNotBlank() }
        .flatMapLatest { kw ->
            flow {
                emit(getAutocompleteAirPorts(kw, 10))
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    //즐겨찾기 결과
    val favorites: StateFlow<List<Favorite>> = repository.getFavorites()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun updateKeyword(new: String) {
        _keyword.value = new
    }

    fun getAirportByCode(code: String): Flow<List<Airport>> = repository.getAirportByCode(code)

    fun insertFavorite(airport: Airport) {

        val favorite = Favorite(
            id = 0,
            departureCode = airport.iataCode,
            destinationCode = ""
        )

        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(airPortCode: String) {

        viewModelScope.launch {
            repository.deleteByDepartureCode(airPortCode)
        }
    }

    suspend fun getAutocompleteAirPorts(keyword: String, limit: Int) =
        repository.getAutocompleteAirPorts(keyword, limit)

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)

                val airportDao = application.database.airportDao()
                val favoriteDao = application.database.favoriteDao()

                val repository = FlightRepository(airportDao, favoriteDao)
                SearchViewModel(repository)
            }
        }
    }
}