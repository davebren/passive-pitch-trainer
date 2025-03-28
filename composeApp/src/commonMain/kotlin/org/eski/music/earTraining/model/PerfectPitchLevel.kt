package org.eski.music.earTraining.model

import org.eski.music.model.Instrument
import org.eski.music.model.Note
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class PerfectPitchLevel(
  val name: String,
  val notes: List<Note>,
  val instruments: List<Instrument>,
  val duration: Duration = 120.toDuration(DurationUnit.SECONDS),
  val promptMaxDuration: Duration = 5.toDuration(DurationUnit.SECONDS),
  val correctRateRequired: Float = .95f,
) {
  val octaveCount = notes.distinctBy { it.octave }.size

  init {
    notes.forEach { note ->
      instruments.firstOrNull { it.canPlayNote(note) }
        ?: throw IllegalArgumentException("Every note must have an instrument that can play it.")
    }
  }

  fun generateNotes(previousNotes: List<Pair<Note, Instrument>>? = null, count: Int): List<Pair<Note, Instrument>> {
    val newNotes = mutableListOf<Pair<Note, Instrument>>()
    var previousNote: Pair<Note, Instrument>? = previousNotes?.lastOrNull()

    while(newNotes.size < count) {
      var nextNote = notes.random()
      if (octaveCount > 1) {
        while (nextNote.octave == previousNote?.first?.octave) {
          nextNote = notes.random()
        }
      }
      val instrument = instruments.filter { it.canPlayNote(nextNote) }.randomOrNull()
        ?: throw IllegalStateException("There is no instrument available for this note.")

      val nextNotePair = Pair(nextNote, instrument)
      newNotes.add(nextNotePair)
      previousNote = nextNotePair
    }

    return newNotes
  }

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