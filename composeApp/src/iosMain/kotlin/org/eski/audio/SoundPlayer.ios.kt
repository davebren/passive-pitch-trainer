package org.eski.audio

import kotlinx.cinterop.ExperimentalForeignApi
import menoback.composeapp.generated.resources.Res
import org.eski.audio.instruments.PianoAudio
import org.eski.music.model.Note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
@ExperimentalResourceApi
actual object SoundPlayer {
  private val noteMap = mutableMapOf<Note, AVAudioPlayer>()
  private val soundEffectMap = mutableMapOf<SoundEffect, AVAudioPlayer>()

  init {
    PianoAudio().availableNotes.forEach { note ->
      val fileName = if (note.natural()) {
        "${note.nameo[0].lowercaseChar()}${note.octave}"
      } else "${note.nameFlat[0].lowercaseChar()}b${note.octave}"

      NSURL.URLWithString(URLString = Res.getUri("files/notes/piano/${fileName}.mp3"))?.let {
        noteMap[note] = AVAudioPlayer(contentsOfURL = it, error = null).apply { prepareToPlay() }
      }
    }

    NSURL.URLWithString(URLString = Res.getUri("files/soundEffects/shortBuzz.mp3"))?.let {
      soundEffectMap[SoundEffect.shortBuzz] = AVAudioPlayer(contentsOfURL = it, error = null).apply { prepareToPlay() }
    }
  }

  actual fun playNote(note: Note) {
//    if (AudioSettings.muted.value) return

    noteMap[note]?.run {
      pause()
      setCurrentTime(0.0)
      play()
    }
  }

  actual fun playSoundEffect(soundEffect: SoundEffect) {
//    if (AudioSettings.muted.value) return

    soundEffectMap[soundEffect]?.run {
      pause()
      setCurrentTime(0.0)
      play()
    }
  }

  actual fun cache(notes: List<Note>) {
  }

}