package org.eski.music.earTraining

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.views.QuitButton
import org.eski.ui.views.selectors.DropDownSelector
import org.eski.ui.views.spacer
import org.eski.ui.views.startButton.StartButton
import org.eski.ui.views.topWindowInsetSpacer


@Composable
fun EarTrainingScreen(
  host: EarTrainingHost,
  vm: EarTrainingViewModel = viewModel { EarTrainingViewModel(host) },
  zIndex: Float,
  topBarMargin: Dp = 0.dp,
) {
  val open by host.earTrainingOpen.collectAsState()
  val size by host.size.collectAsState()
  val quitButtonVisible by vm.quitButtonVisible.collectAsState()

  Box(modifier = Modifier.fillMaxSize()
    .zIndex(zIndex)
  ) {
    val earTrainingLevelSelectorExpanded = remember { mutableStateOf(false) }

    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      topWindowInsetSpacer()
      spacer(topBarMargin)
      EarTrainingActionBar(vm)
      Spacer(Modifier.height(grid))

      Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
          Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            EarTrainingStaff(Modifier.width(200.dp), vm.staff, open)
          }
          Spacer(modifier = Modifier.height(grid2))
        }

        LevelSelector(vm.options, earTrainingLevelSelectorExpanded)
      }
    }

    QuitButton(visible = quitButtonVisible, containerSize = size, onExpanded = { vm.quitClicked() })
    StartButton(vm = vm.startButton, size)
  }
}

@Composable
private fun LevelSelector(vm: EarTrainingOptionsViewModel, expanded: MutableState<Boolean>) {
  val visible by vm.levelSelectorVisible.collectAsState()

  AnimateView(
    visible = visible,
    enter = slideInHorizontally(animationSpec = tween(300, 200), initialOffsetX = { width -> -width }),
    exit = slideOutHorizontally(targetOffsetX = { width -> -width })
  ) {
    DropDownSelector(
      vmx = vm.levelSelectorDropdown,
      expanded = expanded,
      roundedCornerShape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
    )
  }
}
