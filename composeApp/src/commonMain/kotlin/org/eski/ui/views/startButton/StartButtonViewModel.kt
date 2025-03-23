package org.eski.ui.views.startButton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.compose.resources.StringResource

class StartButtonViewModel(
  scope: CoroutineScope,
  startButtonColors: StartButtonColors = StartButtonColors(),
  val goMessageRes: StringResource? = null,
  val gameState: StateFlow<StartButtonGameState>,
  val instructionsHeader: StateFlow<String>,
  val instructionsSubtext: StateFlow<String>,
  private val onPostGameDismissed: () -> Unit,
  private val onStarted: () -> Unit,
) {
  val visible: StateFlow<Boolean> = gameState.map { it != StartButtonGameState.Running }
    .stateIn(scope, SharingStarted.WhileSubscribed(), false)

  private val postGameRestartable = gameState.map { state ->
    if (state == StartButtonGameState.GameOver) {
      delay(1000L) // TODO: Handle cancellation.
      true
    } else false
  }.stateIn(scope, SharingStarted.WhileSubscribed(), false)

  val clickable: StateFlow<Boolean> = combine(gameState, postGameRestartable) { state, postGameRestartable ->
    state != StartButtonGameState.GameOver || postGameRestartable
  }.stateIn(scope, SharingStarted.WhileSubscribed(), true)

  val colors = MutableStateFlow<StartButtonColors>(startButtonColors)

  val icon = gameState.map {
    if (it == StartButtonGameState.GameOver) Icons.Filled.Refresh else Icons.Filled.PlayArrow
  }.stateIn(scope, SharingStarted.WhileSubscribed(), Icons.Filled.PlayArrow)

  val expandFinished = MutableStateFlow(false)
  val startCountdown = expandFinished.map { it }.stateIn(scope, SharingStarted.WhileSubscribed(), false)
  val showInstructions = combine(gameState, expandFinished) { state, expandFinished ->
    state != StartButtonGameState.Paused && expandFinished
  }.stateIn(scope, SharingStarted.WhileSubscribed(), false)


  fun postGameDismissed() { onPostGameDismissed.invoke() }
  fun countdownFinished() { onStarted.invoke() }
}