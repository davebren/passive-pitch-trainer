package org.eski.pitch.ui.game.data

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow

class GameStatsData(val settings: Settings) {
    companion object {
        private const val statsKey = "stats.game"
        private const val gameCountKey = "$statsKey.gameCount"

        const val accuracyThreshold = 90f
        const val defaultLevelUnlocked = 2


        private fun highScoreKey(durationSeconds: Int, level: Int): String {
            return "${statsKey}.highscore.$level"
        }
    }

    val lastScoreUpdate = MutableStateFlow<HighScoreUpdate?>(null)
    val lastUnlockedLevelUpdate = MutableStateFlow<LastUnlockedLevelUpdate?>(null)
    val gameCount = MutableStateFlow<Int>(getTotalGamesPlayed())
    
    fun highScore(durationSeconds: Int, level: Int)
        = settings.getLong(highScoreKey(durationSeconds, level), 0)
    fun putHighScore(score: Long, durationSeconds: Int, level: Int) {
        settings.putLong(highScoreKey(durationSeconds, level), score)
    }

    fun getTotalGamesPlayed(): Int = settings.getInt(gameCountKey, 0)

    fun incrementGamesPlayed() {
        gameCount.value++
        settings.putInt(gameCountKey, gameCount.value)
    }

    data class HighScoreUpdate(val durationSeconds: Int, val level: Int, val score: Long)
    data class LastUnlockedLevelUpdate(val durationSeconds: Int, val level: Int)
}