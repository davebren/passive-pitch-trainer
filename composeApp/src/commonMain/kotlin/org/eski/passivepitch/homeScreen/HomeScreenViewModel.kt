package org.eski.passivepitch.homeScreen

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.EarTrainingHost
import org.eski.pitch.ui.game.data.GameSettings
import org.eski.pitch.ui.game.data.GameStatsData
import org.eski.pitch.ui.game.model.GameState
import org.eski.pitch.ui.game.vm.ValueForValueViewModel
import org.eski.ui.util.screenDensity
import org.eski.ui.util.toDp


class HomeScreenViewModel(
  val gameSettings: GameSettings,
  val stats: GameStatsData,
  override val earTrainingVisibleOnHomeScreen: Boolean = true
): ViewModel(), EarTrainingHost {

  val options = HomeScreenOptionsViewModel(viewModelScope)
  val valueForValue = ValueForValueViewModel(viewModelScope)

  val startButtonVisible = MutableStateFlow<Boolean>(true)
  val gameState = MutableStateFlow<GameState>(GameState.NotStarted)
  val startButtonClickable: StateFlow<Boolean> = gameState.map { it == GameState.NotStarted }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

  val pixelSize = MutableStateFlow(IntSize.Zero)
  override val size: StateFlow<DpSize> = combine(pixelSize, screenDensity) { pixelSize, density ->
    pixelSize.toDp(density)
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DpSize.Zero)

  override val earTrainingOpen = MutableStateFlow<Boolean>(true)

  fun onSizeChanged(pixelSize: IntSize) { this.pixelSize.value = pixelSize }
}