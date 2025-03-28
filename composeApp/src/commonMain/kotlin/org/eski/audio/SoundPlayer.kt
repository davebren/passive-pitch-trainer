package org.eski.audio

import org.eski.music.model.Note


expect object SoundPlayer {

  fun playNote(note: Note, muteable: Boolean = true)
  fun playSoundEffect(soundEffect: SoundEffect)
  fun cache(notes: List<Note>)
}