package org.eski.pitch

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.eski.passivepitch.homeScreen.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
  MaterialTheme {
    HomeScreen()
  }
}