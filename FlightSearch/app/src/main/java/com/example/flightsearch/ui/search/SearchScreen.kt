package com.example.flightsearch.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.ui.components.FlightSearchTopBar
import com.example.flightsearch.ui.items.FlightList

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { FlightSearchTopBar() },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        SearchScreenBody(innerPadding)
    }
}

@Composable
fun SearchScreenBody(
    innerPadding: PaddingValues,
    viewModel: SearchViewModel = viewModel(factory = SearchViewModel.factory)
) {

    val keyword by viewModel.keyword.collectAsState()
    val suggestions by viewModel.suggestions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        OutlinedTextField(
            value = keyword,
            onValueChange = { viewModel.updateKeyword(it) },
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "검색"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "음성 입력",
                )
            },
            singleLine = true,
            placeholder = { Text("검색어를 입력하세요.") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (keyword.isBlank()) {

            val airports = favorites
                .map {
                    viewModel.getAirportByCode(it.departureCode)
                        .collectAsState(initial = emptyList())
                }
                .flatMap { it.value }

            FlightList(
                favorites = favorites,
                airports = airports,
                onFavoriteClick = { airport ->
                    if (favorites.any { it.departureCode == airport.iataCode }) {
                        viewModel.deleteFavorite(airport.iataCode)
                    } else {
                        viewModel.insertFavorite(airport)
                    }
                },
            )
        } else {
            FlightList(
                favorites = favorites,
                airports = suggestions,
                onFavoriteClick = { airport ->
                    if (favorites.any { it.departureCode == airport.iataCode }) {
                        viewModel.deleteFavorite(airport.iataCode)
                    } else {
                        viewModel.insertFavorite(airport)
                    }
                },
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun SearchScreenPreview() {
    SearchScreen()
}