package org.eski.audio.instruments

import org.eski.audio.InstrumentAudio
import org.eski.music.model.Note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import passivepitch.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
class PianoAudio : InstrumentAudio {
  override val availableNotes: List<Note> = Note.entries.subList(12, 84)
  override val availableNotesSet: Set<Note> = availableNotes.toSet()

  override fun resUri(note: Note): String? {
    if (!availableNotesSet.contains(note)) return null

    val fileName = if (note.natural()) {
      "${note.nameo[0].lowercaseChar()}${note.octave}"
    } else "${note.nameFlat[0].lowercaseChar()}b${note.octave}"

    return Res.getUri("files/notes/piano/$fileName.mp3")
  }
}