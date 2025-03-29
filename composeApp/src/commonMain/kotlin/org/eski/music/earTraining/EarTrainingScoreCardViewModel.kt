package org.eski.music.earTraining

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.eski.game.GameMetaState
import org.eski.game.ui.GameScoreCardColors
import org.eski.game.ui.GameScoreCardViewModel
import org.eski.music.earTraining.model.PerfectPitchGameState

class EarTrainingScoreCardViewModel(
  private val scope: CoroutineScope,
  private val gameState: StateFlow<GameMetaState>,
  private val perfectPitch: PerfectPitchGameViewModel
): GameScoreCardViewModel {
  override val visible: StateFlow<Boolean> = gameState.map { it == GameMetaState.Running }
    .stateIn(scope, SharingStarted.WhileSubscribed(), false)

  override val correct: StateFlow<String> = perfectPitch.gameState.map { it?.correctCount?.toString() ?: "" }
    .stateIn(scope, SharingStarted.WhileSubscribed(), "")

  override val incorrect: StateFlow<String> = perfectPitch.gameState.map { it?.incorrectCount?.toString() ?: ""}
    .stateIn(scope, SharingStarted.WhileSubscribed(), "")

  override val timeLeft = MutableStateFlow<String>("")

  override val score = perfectPitch.gameState.map { it?.score?.toString() ?: "" }
    .stateIn(scope, SharingStarted.WhileSubscribed(), "")

  override val colors = MutableStateFlow<GameScoreCardColors>(GameScoreCardColors())

  private var timeUpdateJob: Job? = null

  init {
    scope.launch {
      perfectPitch.gameState.collectLatest { updateTimeLeft(it) }
    }
  }

  private fun updateTimeLeft(gameState: PerfectPitchGameState?) {
    timeUpdateJob?.cancel()

    if (gameState?.ended != false) {
      timeLeft.value = ""
    } else {
      val timeElapsed = Clock.System.now() - gameState.startTime
      val timeLeftDuration = gameState.level.duration - timeElapsed
      timeLeft.value = timeLeftDuration.inWholeSeconds.toString()
      if (timeLeftDuration.inWholeSeconds == 0L) return
      var nextUpdate = timeLeftDuration.inWholeMilliseconds % 1000L
      if (nextUpdate == 0L) nextUpdate = 1000L

      timeUpdateJob = scope.launch {
        delay(nextUpdate)
        updateTimeLeft(perfectPitch.gameState.value)
      }
    }
  }
}