package org.eski.music.earTraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.eski.pitch.ui.game.model.GameState
import org.eski.ui.views.startButton.StartButtonGameState
import org.eski.ui.views.startButton.StartButtonViewModel
import passivepitch.composeapp.generated.resources.Res
import passivepitch.composeapp.generated.resources.go


class EarTrainingViewModel(
  val host: EarTrainingHost
): ViewModel() {
  val gameState = MutableStateFlow(GameState.NotStarted)
  val options = EarTrainingOptionsViewModel(viewModelScope)
  val staff = EarTrainingStaffViewModel(viewModelScope, host, gameState, options.levelSelected)

  val startButtonGameState: StateFlow<StartButtonGameState> = gameState.map {
    when(it) {
      GameState.NotStarted -> StartButtonGameState.NotStarted
      GameState.Running -> StartButtonGameState.Running
      GameState.Paused -> StartButtonGameState.Paused
      GameState.GameOver -> StartButtonGameState.GameOver
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), StartButtonGameState.NotStarted)
  val instructionsHeader = MutableStateFlow<String>("header") // TODO:
  val instructionsSubtext = MutableStateFlow<String>("subtext") // TODO:

  val startButton = StartButtonViewModel(
    scope = viewModelScope,
    goMessageRes = Res.string.go,
    gameState = startButtonGameState,
    instructionsHeader = instructionsHeader,
    instructionsSubtext = instructionsSubtext,
    onStarted = { startGame() },
    onPostGameDismissed = { postGameDismissed() }
  )

  val backButtonVisible = host.earTrainingOpen.map {
    it && !host.earTrainingVisibleOnHomeScreen
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

  fun startGame() {

  }

  fun postGameDismissed() {

  }

  fun backClicked() {

  }
}
