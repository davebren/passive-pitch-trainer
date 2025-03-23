package org.eski.ui.views.startButton

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.eski.ui.animation.AnimateView
import org.eski.ui.animation.AnimatedCountdownTimer
import org.eski.ui.util.grid2
import org.eski.ui.util.grid6
import org.eski.ui.util.grid8
import org.eski.ui.util.square
import org.eski.ui.views.FloatingButton
import org.eski.ui.views.PressExpandButton
import org.eski.ui.views.PressExpandButtonState
import org.eski.ui.views.PressExpandButtonState.Companion.transitionSpecDp
import org.eski.ui.views.PressExpandButtonState.expanded
import org.eski.ui.views.PressExpandButtonState.pressed
import org.eski.ui.views.text.CenteredVerticalText
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StartButton(
  vm: StartButtonViewModel,
  containerSize: DpSize,
) {
  val visible by vm.visible.collectAsState()
  val colors by vm.colors.collectAsState()
  val instructionsHeader by vm.instructionsHeader.collectAsState()
  val instructionsSubtext by vm.instructionsSubtext.collectAsState()
  val showInstructions by vm.showInstructions.collectAsState()
  val startCountdown by vm.startCountdown.collectAsState()

  AnimateView(
    visible = visible,
  ) {
    Column(horizontalAlignment = Alignment.End) {
      Spacer(modifier = Modifier.fillMaxSize().weight(1f))

      AnimateView(
        visible = visible,
        enter = slideInVertically(animationSpec = tween(300, 0)) { height -> height },
        exit = slideOutVertically(animationSpec = tween(300, 0)) { height -> height }
      ) {
        val unpressedSize = FloatingButton.large.unpressedSize.output(containerSize.height).square()
        val pressedSize = FloatingButton.large.pressedSize.output(containerSize.height).square()
        val verticalPadding = FloatingButton.large.bottomMargin.output(containerSize.height)

        PressExpandButton(
          expandedSize = containerSize,
          offset = DpOffset(0.dp, 0.dp),
          size = PressExpandButtonState.Map(
            unpressed = unpressedSize,
            pressed = pressedSize,
            expanded = containerSize
          ),
          elevation = PressExpandButtonState.Map(unpressed = 16.dp, 32.dp, 64.dp),
          horizontalPadding = grid2,
          verticalPadding = verticalPadding,
          cornerRadius = PressExpandButtonState.Map(unpressed = 40.dp, pressed = 44.dp, expanded = 0.dp),
          backgroundColor = PressExpandButtonState.staticMap(colors.background),
          border = BorderStroke(4.dp, colors.border),
          clickable = vm.clickable,
        ) { transition ->
          vm.expandFinished.value = transition.currentState == expanded && transition.targetState == expanded

          if (transition.currentState == pressed && transition.targetState == expanded) {
            vm.postGameDismissed()
          }

          if (transition.targetState != expanded) {
            val iconSize by transition.animateDp(transitionSpecDp) { targetState ->
              when(targetState) {
                PressExpandButtonState.unpressed -> grid6
                pressed -> grid8
                expanded -> 0.dp
              }
            }

            Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.size(grid6),
            ) {
              val vector by vm.icon.collectAsState()
              Icon(
                imageVector = vector,
                contentDescription = null,
                tint = colors.icon,
                modifier = Modifier.size(iconSize)
              )
            }
          }

          if (showInstructions) {
            StartButtonInstructions(instructionsHeader, instructionsSubtext, colors.instructions)
          }
          if (startCountdown) {
            Countdown(
              goMessageRes = vm.goMessageRes,
              countdownColor = colors.countdown,
              goColor = colors.go,
              onFinished = { vm.countdownFinished() },
            )
          }
        }
      }
    }
  }
}

@Composable
private fun Countdown(
  goMessageRes: StringResource?,
  countdownColor: Color,
  goColor: Color,
  onFinished: () -> Unit,
) {
  var timer by remember { mutableStateOf(3) }
  val coroutineScope = rememberCoroutineScope()
  val animatedCountdownTimer = remember { AnimatedCountdownTimer(coroutineScope) }
  val goMessage = goMessageRes?.let { res -> stringResource(res) } ?: "Go!"

  CenteredVerticalText(
    modifier = Modifier.graphicsLayer {
      scaleX = animatedCountdownTimer.scale
      scaleY = animatedCountdownTimer.scale
      alpha = animatedCountdownTimer.alpha
    },
    text = if (timer == 0) goMessage else "$timer",
    fontSize = 30.sp,
    color = if (timer == 0) goColor else countdownColor
  )

  LaunchedEffect(Unit) {
    animatedCountdownTimer.start(3, 0) {
      timer = it
      if (timer == 0) onFinished.invoke()
    }
  }
}