package org.eski.audio

import android.annotation.SuppressLint
import android.media.SoundPool
import android.media.SoundPool.OnLoadCompleteListener
import org.eski.audio.instruments.PianoAudio
import org.eski.menoback.R
import org.eski.music.model.Note
import org.eski.pitch.AndroidApp


actual object SoundPlayer {
  val soundPool: SoundPool = SoundPool.Builder()
    .setMaxStreams(10)
    .build()

  private val noteIdMap = mutableMapOf<Note, Int>()
  private val soundEffectIdMap = mutableMapOf<SoundEffect, Int>()

  val loadListener: OnLoadCompleteListener = OnLoadCompleteListener { soundPool, index, status -> }

  init {
    soundPool.setOnLoadCompleteListener(loadListener)
    PianoAudio().availableNotes.forEach { note ->
      val resName = if (!note.natural()) {
        "piano_ff_${note.nameFlat[0].lowercaseChar()}b${note.octave}"
      } else "piano_ff_${note.nameo[0].lowercaseChar()}${note.octave}"

      val soundId = soundPool.load(AndroidApp.instance, getRawResId(resName), 1)
      noteIdMap[note] = soundId
    }

    soundEffectIdMap[SoundEffect.shortBuzz] = soundPool.load(AndroidApp.instance, R.raw.soundeffect_short_buzz, 1)
  }

  @SuppressLint("DiscouragedApi")
  private fun getRawResId(resName: String) = AndroidApp.instance.resources.getIdentifier(resName, "raw", AndroidApp.instance.packageName)

  fun init() {}

  actual fun playNote(note: Note) {
//    if (AudioSettings.muted.value) return

    noteIdMap[note]?.let { soundPool.play(it, 0.99f, 0.99f, 1, 0, 1f) }
  }

  actual fun playSoundEffect(soundEffect: SoundEffect) {
//    if (AudioSettings.muted.value) return

    soundEffectIdMap[soundEffect]?.let { soundPool.play(it, 0.99f, 0.99f, 1, 0, 1f) }
  }

  // Not needed on android.
  actual fun cache(notes: List<Note>) {}
}