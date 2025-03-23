package org.eski.pitch

import android.app.Application
import org.eski.audio.SoundPlayer

class AndroidApp: Application() {
  companion object {
    lateinit var instance: AndroidApp
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    SoundPlayer.init()
  }
}