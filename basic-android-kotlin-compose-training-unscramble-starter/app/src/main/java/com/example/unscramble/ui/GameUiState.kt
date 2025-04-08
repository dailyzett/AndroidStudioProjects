package com.example.unscramble.ui

data class GameUiState(
    val currentScrambledWorld: String = "",
    val isGuessedWordWrong: Boolean = false,
)
