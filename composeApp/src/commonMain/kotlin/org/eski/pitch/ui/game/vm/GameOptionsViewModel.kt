package org.eski.pitch.ui.game.vm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import org.eski.music.earTraining.data.introSettings

class GameOptionsViewModel(
  scope: CoroutineScope,
) {
  val introShowing = MutableStateFlow<Boolean>(false)
  val settingsShowing = MutableStateFlow<Boolean>(false)
  val achievementsShowing = MutableStateFlow<Boolean>(false)

  init {
    scope.launch {
      introSettings.introShown.takeWhile { !it }.collectLatest {
        introShowing.value = !it
        if (!it) introSettings.setIntroShown(true)
      }
    }
  }
  fun introClicked() {
    introShowing.value = true
  }
  fun introDismissed() {
    introShowing.value = false
  }

  fun settingsClicked() {
    settingsShowing.value = true
  }
  fun settingsDismissed() {
    settingsShowing.value = false
  }

  fun achievementsClicked() {
    achievementsShowing.value = true
  }
  fun achievementsDismissed() {
    achievementsShowing.value = false
  }
}