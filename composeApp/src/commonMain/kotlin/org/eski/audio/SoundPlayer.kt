package org.eski.audio

import org.eski.music.model.Note


expect object SoundPlayer {

  fun playNote(note: Note)
  fun playSoundEffect(soundEffect: SoundEffect)
  fun cache(notes: List<Note>)
}