package org.eski.pitch

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.eski.music.earTraining.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
  MaterialTheme {
    HomeScreen()
  }
}