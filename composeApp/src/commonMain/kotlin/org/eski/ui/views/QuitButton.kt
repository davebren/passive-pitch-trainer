package org.eski.ui.views

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid2
import org.eski.ui.util.grid6
import org.eski.ui.util.grid8
import org.eski.ui.util.square
import org.eski.ui.views.PressExpandButtonState.Companion.transitionSpecDp
import org.eski.ui.views.PressExpandButtonState.expanded
import org.eski.ui.views.PressExpandButtonState.pressed

@Composable
fun QuitButton(
  visible: Boolean,
  containerSize: DpSize,
  onExpanded: () -> Unit,
  background: Color = Color.Red,
  iconColor: Color = Color.White,
  borderColor: Color = Color.DarkGray,
) {
  // TODO: Add QuitButtonViewModel
  val expandedFinished = remember { mutableStateOf(false) }

  Column(horizontalAlignment = Alignment.Start) {
    Spacer(modifier = Modifier.fillMaxSize().weight(1f))

    AnimateView(
      visible = visible,
      enter = slideInVertically(animationSpec = tween(300, 0)) { height -> height },
      exit = if (expandedFinished.value) {
        fadeOut()
      } else {
        slideOutVertically(animationSpec = tween(300, 0)) { height -> height }
      }
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
        backgroundColor = PressExpandButtonState.staticMap(background),
        border = BorderStroke(4.dp, borderColor)
      ) { transition ->
        expandedFinished.value = transition.currentState == expanded && transition.targetState == expanded

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
            Icon(
              imageVector = Icons.Filled.Clear,
              contentDescription = null,
              tint = iconColor,
              modifier = Modifier.size(iconSize)
            )
          }
        }

        if (expandedFinished.value) {
          onExpanded.invoke()
        }
      }
    }
  }
}