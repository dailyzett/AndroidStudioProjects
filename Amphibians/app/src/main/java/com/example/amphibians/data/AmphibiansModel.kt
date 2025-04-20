package com.example.amphibians.data

import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansModel(
    val name: String,
    val type: String,
    val description: String,
    val imgSrc: String
)