package org.eski.passivepitch.homeScreen

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.EarTrainingHost
import org.eski.intro.IntroSettings
import org.eski.ui.views.valueForValue.ValueForValueViewModel
import org.eski.ui.util.screenDensity
import org.eski.ui.util.toDp


class HomeScreenViewModel(
  introSettings: IntroSettings,
  override val earTrainingVisibleOnHomeScreen: Boolean = true
): ViewModel(), EarTrainingHost {

  val options = HomeScreenOptionsViewModel(viewModelScope, introSettings)
  val valueForValue = ValueForValueViewModel(viewModelScope)

  val pixelSize = MutableStateFlow(IntSize.Zero)
  override val size: StateFlow<DpSize> = combine(pixelSize, screenDensity) { pixelSize, density ->
    pixelSize.toDp(density)
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), DpSize.Zero)

  override val earTrainingOpen = MutableStateFlow<Boolean>(true)

  fun onSizeChanged(pixelSize: IntSize) { this.pixelSize.value = pixelSize }
}