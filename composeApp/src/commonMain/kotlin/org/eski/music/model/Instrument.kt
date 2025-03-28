package org.eski.music.model

enum class Instrument(val bottomNote: Note, val topNote: Note) {
  piano(bottomNote = Note.c1, topNote = Note.g7);

  val noteRange = Note.entries.indexOf(bottomNote)..Note.entries.indexOf(topNote)

  fun canPlayNote(note: Note): Boolean = noteRange.contains(Note.entries.indexOf(note))
}