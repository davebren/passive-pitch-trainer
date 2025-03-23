package org.eski.music.earTraining

import androidx.compose.ui.unit.DpSize
import kotlinx.coroutines.flow.StateFlow

interface EarTrainingHost {
  val size: StateFlow<DpSize>
  val earTrainingOpen: StateFlow<Boolean>
  val earTrainingVisibleOnHomeScreen: Boolean
}
