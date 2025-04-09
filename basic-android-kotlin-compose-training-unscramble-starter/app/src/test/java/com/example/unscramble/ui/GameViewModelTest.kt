package com.example.unscramble.ui

import com.example.unscramble.data.getUnscrambledWord
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset()  {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWorld)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value

        Assertions.assertEquals(currentGameUiState.isGuessedWordWrong, false)
        Assertions.assertEquals(currentGameUiState.score, 20)
    }
}