package org.eski.audio

import javazoom.jl.player.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.eski.music.model.Note
import java.io.BufferedInputStream


actual object SoundPlayer {
  actual fun playNote(note: Note, muteable: Boolean) {
//    if (muteable && AudioSettings.muted.value) return
    println("playNote")

    val fileName = if (note.natural()) {
      "${note.nameo[0].lowercaseChar()}${note.octave}"
    } else "${note.nameFlat[0].lowercaseChar()}b${note.octave}"

    playSoundFromResource("/composeResources/passivepitch.composeapp.generated.resources/files/notes/piano/${fileName}.mp3")
  }

  actual fun playSoundEffect(soundEffect: SoundEffect) {
//    if (AudioSettings.muted.value) return
    playSoundFromResource("/composeResources/passivepitch.composeapp.generated.resources/files/soundEffects/${soundEffect.fileName}")
  }

  private fun playSoundFromResource(jarRelativePath: String) {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        javaClass.getResourceAsStream(jarRelativePath)
          .use { stream ->
            val buffer = BufferedInputStream(stream)
            val player = Player(buffer) // TODO: Can this be cached in memory?
            player.play()
          }
      } catch (error: Exception) {
        print(error)
        // TODO: Do something.
      }
    }
  }

  actual fun cache(notes: List<Note>) {
  }
}