package org.eski.music.earTraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.eski.audio.SoundPlayer
import org.eski.pitch.ui.game.model.GameMetaState
import org.eski.ui.views.startButton.StartButtonGameState
import org.eski.ui.views.startButton.StartButtonViewModel
import passivepitch.composeapp.generated.resources.Res
import passivepitch.composeapp.generated.resources.go


class EarTrainingViewModel(
  val host: EarTrainingHost
): ViewModel() {
  val gameMetaState = MutableStateFlow(GameMetaState.NotStarted)
  val perfectPitchGame = PerfectPitchGameViewModel(viewModelScope, SoundPlayer)
  val options = EarTrainingOptionsViewModel(viewModelScope, gameMetaState)
  val staff = EarTrainingStaffViewModel(viewModelScope, host, gameMetaState, options.levelSelected, perfectPitchGame)

  val startButtonGameState: StateFlow<StartButtonGameState> = gameMetaState.map {
    when(it) {
      GameMetaState.NotStarted -> StartButtonGameState.NotStarted
      GameMetaState.Running -> StartButtonGameState.Running
      GameMetaState.Paused -> StartButtonGameState.Paused
      GameMetaState.GameOver -> StartButtonGameState.GameOver
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), StartButtonGameState.NotStarted)
  val instructionsHeader = options.levelSelected.map {
    "Perfect Pitch - ${it.name}"
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "")
  val instructionsSubtext = MutableStateFlow<String>("Select the correct note after it is played.")

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

  init {
    viewModelScope.launch {
      perfectPitchGame.gameState.collectLatest { state ->
        if (gameMetaState.value == GameMetaState.Running && state?.ended == true) {
          gameMetaState.value = GameMetaState.GameOver
        }
      }
    }
  }

  fun startGame() {
    gameMetaState.value = GameMetaState.Running
    perfectPitchGame.startGame(options.levelSelected.value)
  }

  fun postGameDismissed() {
    gameMetaState.value = GameMetaState.NotStarted
  }

  fun backClicked() {

  }
}
