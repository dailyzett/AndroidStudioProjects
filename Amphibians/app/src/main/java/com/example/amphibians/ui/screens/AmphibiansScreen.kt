package com.example.amphibians.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.data.AmphibiansModel

@Composable
fun AmphibiansScreen(
    modifier: Modifier = Modifier,
    amphibians: List<AmphibiansModel>
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(amphibians) {
            AmphibiansCard(data = it)
        }
    }
}

@Composable
fun AmphibiansCard(
    modifier: Modifier = Modifier,
    data: AmphibiansModel,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = data.name,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F0E8))
                .padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Justify
        )

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(data.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Text(
            text = data.description,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8F0E8))
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 24.sp,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
@Preview
fun AmphibiansCardPreview() {

}