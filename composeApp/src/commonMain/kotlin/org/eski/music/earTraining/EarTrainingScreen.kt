package org.eski.music.earTraining

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.atomicfu.TraceBase.None.append
import org.eski.game.GameSettings
import org.eski.game.ui.GameScoreCard
import org.eski.game.ui.HighScoreCard
import org.eski.music.earTraining.options.EarTrainingOptionColors
import org.eski.music.earTraining.options.EarTrainingOptionsViewModel
import org.eski.pitch.ui.game.data.EarTrainingStatsData
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.util.gridHalf
import org.eski.ui.views.QuitButton
import org.eski.ui.views.feedback.FeedbackIconAnimation
import org.eski.ui.views.feedback.feedbackFlashAnimation
import org.eski.ui.views.selectors.DropDownSelector
import org.eski.ui.views.spacer
import org.eski.ui.views.startButton.StartButton
import org.eski.ui.views.text.CenteredVerticalText
import org.eski.ui.views.topWindowInsetSpacer


@Composable
fun EarTrainingScreen(
  host: EarTrainingHost,
  gameSettings: GameSettings,
  statsData: EarTrainingStatsData,
  vm: EarTrainingViewModel = viewModel { EarTrainingViewModel(host, gameSettings, statsData) },
  zIndex: Float,
  topBarMargin: Dp = 0.dp,
) {
  val open by host.earTrainingOpen.collectAsState()
  val size by host.size.collectAsState()
  val quitButtonVisible by vm.quitButtonVisible.collectAsState()
  val highScoreVisible by vm.options.highScoreVisible.collectAsState()
  val highScore by vm.options.highScoreText.collectAsState()
  val levelSelectorVisible by vm.options.levelSelectorVisible.collectAsState()
  val optionColors by vm.options.optionColors.collectAsState()

  Box(modifier = Modifier.fillMaxSize()
    .zIndex(zIndex).feedbackFlashAnimation(vm.feedback)
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

        Column(modifier = Modifier.fillMaxSize()) {
          GameScoreCard(vm.scoreCard)
        }

        Column(modifier = Modifier.fillMaxSize()) {
          HighScoreCard(highScoreVisible, highScore, roundedCornerShape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
          LevelSelector(levelSelectorVisible, vm.options, optionColors, earTrainingLevelSelectorExpanded)
          LevelSubtext(levelSelectorVisible, vm.options, optionColors)
        }
      }
    }
    FeedbackIconAnimation(vm.feedback)

    QuitButton(visible = quitButtonVisible, containerSize = size, onExpanded = { vm.quitClicked() })
    StartButton(vm = vm.startButton, size)
  }
}

@Composable
private fun LevelSelector(
  visible: Boolean,
  vm: EarTrainingOptionsViewModel,
  optionColors: EarTrainingOptionColors,
  expanded: MutableState<Boolean>
) {
  AnimateView(
    visible = visible,
    enter = slideInHorizontally(animationSpec = tween(300, 200), initialOffsetX = { width -> -width }),
    exit = slideOutHorizontally(targetOffsetX = { width -> -width })
  ) {
    DropDownSelector(
      vmx = vm.levelSelectorDropdown,
      expanded = expanded,
      roundedCornerShape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp),
      background = optionColors.cardBackground,
      textColor = optionColors.textColor,
    )
  }
}

@Composable
private fun LevelSubtext(
  visible: Boolean,
  vm: EarTrainingOptionsViewModel,
  optionColors: EarTrainingOptionColors
) {
  val subtext by vm.levelSubtextAnnotated.collectAsState()

  AnimateView(
    visible = visible,
    enter = slideInHorizontally(animationSpec = tween(300, 200), initialOffsetX = { width -> -width }),
    exit = slideOutHorizontally(targetOffsetX = { width -> -width })
  ) {
    Card(
      backgroundColor = optionColors.cardBackground,
      shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
    ) {
      CenteredVerticalText(
        modifier = Modifier.padding(horizontal = grid2, vertical = grid),
        annotatedText = subtext,
        color = optionColors.textColor
      )
    }
  }
}