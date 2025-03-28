package org.eski.passivepitch

import com.russhwolf.settings.Settings
import org.eski.game.GameSettings
import org.eski.intro.IntroSettings
import org.eski.pitch.ui.game.data.GameStatsData
import org.eski.pitch.ui.keybinding.KeyBindingSettings

internal val settings = Settings()

internal val keyBindingSettings = KeyBindingSettings(org.eski.music.earTraining.data.settings)
internal val gameStatsData = GameStatsData(org.eski.music.earTraining.data.settings)
internal val gameSettings = GameSettings(org.eski.music.earTraining.data.settings)
internal val introSettings = IntroSettings(org.eski.music.earTraining.data.settings)
