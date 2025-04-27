package com.example.flightsearch.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

@Composable
fun FlightList(
    favorites: List<Favorite>,
    airports: List<Airport>,
    onFavoriteClick: (Airport) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(airports) { airport ->

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("DEPART", style = MaterialTheme.typography.labelSmall)
                        Text(
                            text = "${airport.iataCode} ${airport.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(Modifier.height(8.dp))
                    }

                    IconButton(onClick = { onFavoriteClick(airport) }) {
                        Icon(
                            imageVector = if (favorites.any { it.departureCode == airport.iataCode })
                                Icons.Default.Star
                            else
                                Icons.Default.StarBorder,
                            contentDescription = "즐겨찾기"
                        )
                    }
                }
            }
        }
    }
}