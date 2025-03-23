package org.eski.audio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.eski.audio.instruments.PianoAudio
import org.eski.music.model.Note
import org.w3c.dom.Audio


actual object SoundPlayer {
  private const val cacheAhead = 3

  private val cachedNotes = mutableMapOf<Note, MutableList<Audio>>()
  private val cachedSoundEffects = mutableMapOf<SoundEffect, MutableList<Audio>>()

  var instrumentAudio: InstrumentAudio = PianoAudio()

  init {
    cache(Note.octave(4))
    instrumentAudio.availableNotes.forEach { note ->
      instrumentAudio.resUri(note)?.let { uri ->
        val cachedList = mutableListOf<Audio>()

        for(i in 0 until cacheAhead) {
          cachedList.add(Audio(uri).apply { load() })
        }
        cachedNotes[note] = cachedList
      }
    }

    SoundEffect.entries.forEach { soundEffect ->
      cachedSoundEffects[soundEffect] = (0 until cacheAhead).map {
        Audio(soundEffect.resUri()).apply { load() }
      }.toMutableList()
    }
  }

  actual fun playNote(note: Note) {
//    if (AudioSettings.muted.value) return
    cachedNotes[note]?.let { playFromCache(it, 5000L) }
  }

  actual fun playSoundEffect(soundEffect: SoundEffect) {
//    if (AudioSettings.muted.value) return
    cachedSoundEffects[soundEffect]?.let { playFromCache(it, 1L) }
  }

  private fun playFromCache(cachedAudio: MutableList<Audio>, addBackDelayMillis: Long) {
    CoroutineScope(Dispatchers.Default).launch {
      val audio = cachedAudio.removeFirstOrNull() ?: return@launch
      audio.play()

      // This is a workaround for issues with using the javascript callback functions.
      delay(addBackDelayMillis)
      cachedAudio.add(audio)
    }
  }

  actual fun cache(notes: List<Note>) {
    notes.forEach { note ->
      instrumentAudio.resUri(note)?.let { uri ->
        val cachedList = cachedNotes[note] ?: mutableListOf()

        for(i in cachedList.size until cacheAhead) {
          cachedList.add(Audio(uri).apply { load() })
        }
        cachedNotes[note] = cachedList
      }
    }
  }
}