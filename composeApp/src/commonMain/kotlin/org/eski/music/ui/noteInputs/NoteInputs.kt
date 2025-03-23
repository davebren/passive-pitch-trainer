package org.eski.music.ui.noteInputs

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.eski.music.model.Note

object NoteInputs {
  val notePressedFlow = MutableSharedFlow<Note>(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
  )

  val notePressCancelledFlow = MutableSharedFlow<Note>(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
  )

  val noteClickedFlow = MutableSharedFlow<Note>(
    replay = 1,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
  )
}