package org.eski.audio

import org.eski.music.model.Note

interface InstrumentAudio {
  val availableNotes: List<Note>
  val availableNotesSet: Set<Note>

  fun resUri(note: Note): String?
}