package org.eski.audio

import org.jetbrains.compose.resources.ExperimentalResourceApi
import passivepitch.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
enum class SoundEffect(val fileName: String) {
  shortBuzz("shortBuzz.mp3");

  fun resUri() = Res.getUri("files/soundEffects/$fileName")
}