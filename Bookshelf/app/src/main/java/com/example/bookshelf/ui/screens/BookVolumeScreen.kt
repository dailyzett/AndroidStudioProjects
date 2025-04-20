package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.network.BookVolume

@Composable
fun BookVolumeScreen(
    bookVolume: BookVolume
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val thumbnailUrl = bookVolume.volumeInfo?.imageLinks?.thumbnail

        val secureUrl = thumbnailUrl?.replace("http://", "https://")

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(secureUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }

}