package org.eski.music.earTraining.data

import com.russhwolf.settings.Settings
import org.eski.pitch.ui.keybinding.KeyBindingSettings
import org.eski.pitch.ui.game.data.GameSettings
import org.eski.pitch.ui.game.data.GameStatsData
import org.eski.pitch.ui.game.data.IntroSettings

internal val settings = Settings()

internal val keyBindingSettings = KeyBindingSettings(settings)
internal val gameStatsData = GameStatsData(settings)
internal val gameSettings = GameSettings(settings)
internal val introSettings = IntroSettings(settings)
