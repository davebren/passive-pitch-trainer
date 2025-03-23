package org.eski.pitch.ui.game.views.feedback

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import org.eski.pitch.ui.game.model.FeedbackMode
import org.eski.pitch.ui.game.model.FeedbackState

private const val feedbackDurationMillis = 300

@Composable
fun Modifier.feedbackFlashAnimation(): Modifier = composed {
  val feedbackState = FeedbackState.none // TODO: Get from viewmodel.
  val feedbackMode = listOf(FeedbackMode.icon, FeedbackMode.flashBackground) // TODO: Get from viewmodel.

  if (!feedbackMode.contains(FeedbackMode.flashBackground) || feedbackState == FeedbackState.none)
    return@composed this@composed

  var animationTriggered by remember { mutableStateOf(false) }
  val animationAlpha by animateFloatAsState(
    targetValue = if (animationTriggered) 0.3f else 0.1f,
    animationSpec = tween(durationMillis = feedbackDurationMillis / 2),
    label = "feedbackAlpha"
  )

  val feedbackColor = when (feedbackState) {
    FeedbackState.correct -> Color.Green
    FeedbackState.incorrect -> Color.Red
    FeedbackState.none -> throw IllegalStateException()
  }

  LaunchedEffect(feedbackState) {
    animationTriggered = true
    delay((feedbackDurationMillis / 2).toLong())
    animationTriggered = false
  }


  return@composed drawBehind {
    drawRect(
      color = feedbackColor.copy(alpha = animationAlpha),
      size = size
    )
  }
}