package com.example.bookshelf.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bookshelf.network.JazzHistory

@Composable
fun JazzHistoryScreen(
    jazzHistory: JazzHistory
) {
    Text(
        text = jazzHistory.kind
    )
}