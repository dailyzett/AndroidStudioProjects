package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookVolume(
    val volumeInfo: VolumeInfo? = null,
) {
    @Serializable
    data class VolumeInfo(
        val imageLinks: ImageLinks? = null,
    ) {
        @Serializable
        data class ImageLinks(
            val thumbnail: String? = null,
        )
    }
}
