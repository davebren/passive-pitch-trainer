package org.eski.pitch.ui.keybinding

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type

const val repeatDelayMillis = 250L
const val repeatTickMillis = 50L

@Composable
fun KeyboardInput(
  keyBindingSettings: KeyBindingSettings
) {
  val focusRequester = remember { FocusRequester() }
  var hasFocus by remember { mutableStateOf(false) }

  val startGame by keyBindingSettings.startGame.collectAsState()
  val pauseGame by keyBindingSettings.pauseGame.collectAsState()

  Box(modifier = Modifier
    .focusRequester(focusRequester)
    .onFocusChanged { hasFocus = it.isFocused }
    .focusable()
    .onKeyEvent { event ->
      if (event.isCtrlPressed || event.isShiftPressed || event.isMetaPressed || event.isAltPressed) {
        return@onKeyEvent false
      }

      val keyCode = event.key.keyCode

      if (event.type == KeyEventType.KeyDown) {
        when (keyCode) {
          startGame -> {}// TODO
          pauseGame -> {}// TODO
        }
      }

      if (event.type == KeyEventType.KeyUp) {
        when(keyCode) {
        }
      }

      return@onKeyEvent false
    }
  )

  if (!hasFocus) {
    LaunchedEffect(Unit) {
      focusRequester.requestFocus()
    }
  }
}