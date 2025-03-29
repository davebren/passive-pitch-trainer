package org.eski.music.earTraining.model

import org.eski.music.model.Instrument
import org.eski.music.model.Note
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class PerfectPitchLevel(
  val index: Int,
  val name: String = "Level $index",
  val subtextUnformatted: String,
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

  fun subtext() = subtextUnformatted.replace("{instruments}", instruments.joinToString(", "))

  companion object {
    val levels = listOf(
      PerfectPitchLevel(
        index = 1,
        notes = listOf(Note.c4, Note.a4),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C | 1 | {instruments}",
      ),
      PerfectPitchLevel(
        index = 2,
        notes = listOf(Note.c4, Note.a4, Note.c3, Note.a3),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C | 2 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 3,
        notes = listOf(Note.c4, Note.a4, Note.c3, Note.a3, Note.c5, Note.a5),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C | 3 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 4,
        notes = listOf(Note.c4, Note.a4, Note.c3, Note.a3, Note.c5, Note.a5, Note.c2, Note.a2),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C | 4 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 5,
        notes = listOf(Note.c4, Note.a4, Note.c3, Note.a3, Note.c5, Note.a5, Note.c2, Note.a2),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C | 5 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 6,
        notes = listOf(Note.c4, Note.e4, Note.c3, Note.e3, Note.c5, Note.e5, Note.c2, Note.e2),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "C, E | 1 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 7,
        notes = listOf(Note.e4, Note.a4, Note.e3, Note.a3, Note.e5, Note.a5, Note.e2, Note.a2),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, E | 1 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 8,
        notes = listOf(Note.e4, Note.a4, Note.c4),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C, E | 1 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 9,
        notes = listOf(Note.e4, Note.a4, Note.c4, Note.e3, Note.a3, Note.c3),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C, E | 2 | {instruments}"
      ),
      PerfectPitchLevel(
        index = 10,
        notes = listOf(Note.e4, Note.a4, Note.c4, Note.e3, Note.a3, Note.c3),
        instruments = listOf(Instrument.piano),
        subtextUnformatted = "A, C, E | 2 | {instruments}"
      ),
    )
  }
}