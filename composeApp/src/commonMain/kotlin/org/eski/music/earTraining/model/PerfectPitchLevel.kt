package org.eski.music.earTraining.model

import org.eski.music.model.Instrument
import org.eski.music.model.Note

data class PerfectPitchLevel(
  val name: String,
  val notes: List<Note>,
  val instruments: List<Instrument>,
) {
  companion object {
    val levels = listOf(
      PerfectPitchLevel(
        name = "Level 1",
        notes = listOf(Note.c4, Note.a4),
        instruments = listOf(Instrument.piano)
      ),
      PerfectPitchLevel(
        name = "Level 2",
        notes = listOf(Note.c4, Note.a4, Note.c3, Note.a3),
        instruments = listOf(Instrument.piano)
      )
    )
  }
}