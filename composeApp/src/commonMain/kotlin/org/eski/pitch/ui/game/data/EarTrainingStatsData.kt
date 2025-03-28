package org.eski.pitch.ui.game.data

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow

class EarTrainingStatsData(val settings: Settings) {
    companion object {
        private const val statsKey = "stats.earTraining"
        private const val perfectPitchKey = "$statsKey.perfectPitch"
        private const val gameCountKey = "$statsKey.gameCount"

        const val accuracyThreshold = 90f
        const val defaultLevelUnlocked = 2

        private fun perfectPitchHighScoreKey(level: Int): String {
            return "${perfectPitchKey}.highscore.$level"
        }
    }

    val lastScoreUpdate = MutableStateFlow<HighScoreUpdate?>(null)
    val lastUnlockedLevelUpdate = MutableStateFlow<LastUnlockedLevelUpdate?>(null)
    val gameCount = MutableStateFlow<Int>(getTotalGamesPlayed())
    
    fun perfectPitchHighScore(level: Int)
        = settings.getLong(perfectPitchHighScoreKey(level), 0)

    fun putPerfectPitchHighScore(score: Long, level: Int) {
        settings.putLong(perfectPitchHighScoreKey(level), score)
    }

    fun getTotalGamesPlayed(): Int = settings.getInt(gameCountKey, 0)

    fun incrementGamesPlayed() {
        gameCount.value++
        settings.putInt(gameCountKey, gameCount.value)
    }

    data class HighScoreUpdate(val level: Int, val score: Long)
    data class LastUnlockedLevelUpdate(val level: Int)
}