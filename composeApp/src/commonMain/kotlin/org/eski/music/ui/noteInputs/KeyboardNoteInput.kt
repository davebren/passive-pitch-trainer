package org.eski.music.ui.noteInputs

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import org.eski.music.model.Note

@Composable
fun KeyboardNoteInput() {
  val focusRequester = remember { FocusRequester() }
  var hasFocus by remember { mutableStateOf(false) }

  Box(modifier = Modifier
    .focusRequester(focusRequester)
    .onFocusChanged { hasFocus = it.isFocused }
    .focusable()
    .onKeyEvent { event ->
      if (event.type != KeyEventType.KeyUp) return@onKeyEvent false

      if (!event.isShiftPressed && !event.isCtrlPressed) {
        when(event.key) {
          Key.A -> NoteInputs.noteClickedFlow.tryEmit(Note.a4)
          Key.B -> NoteInputs.noteClickedFlow.tryEmit(Note.b4)
          Key.C -> NoteInputs.noteClickedFlow.tryEmit(Note.c4)
          Key.D -> NoteInputs.noteClickedFlow.tryEmit(Note.d4)
          Key.E -> NoteInputs.noteClickedFlow.tryEmit(Note.e4)
          Key.F -> NoteInputs.noteClickedFlow.tryEmit(Note.f4)
          Key.G -> NoteInputs.noteClickedFlow.tryEmit(Note.g4)
        }
      } else if (event.isShiftPressed) {
        when(event.key) {
          Key.A -> NoteInputs.noteClickedFlow.tryEmit(Note.as4)
          Key.C -> NoteInputs.noteClickedFlow.tryEmit(Note.cs4)
          Key.D -> NoteInputs.noteClickedFlow.tryEmit(Note.ds4)
          Key.F -> NoteInputs.noteClickedFlow.tryEmit(Note.fs4)
          Key.G -> NoteInputs.noteClickedFlow.tryEmit(Note.gs4)
        }
      } else if (event.isCtrlPressed) {
        when(event.key) {
          Key.A -> NoteInputs.noteClickedFlow.tryEmit(Note.g4)
          Key.B -> NoteInputs.noteClickedFlow.tryEmit(Note.as4)
          Key.D -> NoteInputs.noteClickedFlow.tryEmit(Note.cs4)
          Key.E -> NoteInputs.noteClickedFlow.tryEmit(Note.ds4)
          Key.G -> NoteInputs.noteClickedFlow.tryEmit(Note.fs4)
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