package org.eski.pitch

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(
        position = WindowPosition(Alignment.Center), size = DpSize(640. dp, 768.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "PassivePitch",
        state = state,
    ) {
        App()
    }
}