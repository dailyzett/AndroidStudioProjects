package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class JazzHistory(
    val kind: String,
    val totalItems: Long,
    val items: List<JazzHistoryItem>
) {
    @Serializable
    data class JazzHistoryItem(
        val kind: String,
        val id: String,
        val etag: String
    )
}
