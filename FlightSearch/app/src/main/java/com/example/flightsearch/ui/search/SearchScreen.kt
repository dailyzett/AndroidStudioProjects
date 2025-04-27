package com.example.flightsearch.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flightsearch.ui.components.FlightSearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
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
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        Text(text = "Hello World")
    }
}

@Composable
@Preview(showBackground = false)
fun SearchScreenPreview() {
    SearchScreen()
}