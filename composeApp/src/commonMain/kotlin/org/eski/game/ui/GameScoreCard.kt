package org.eski.game.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid2
import org.eski.ui.util.gridHalf
import org.eski.ui.views.text.CenteredVerticalText

@Composable
fun GameScoreCard(
  vm: GameScoreCardViewModel
) {
  val visible by vm.visible.collectAsState()
  val correct by vm.correct.collectAsState()
  val incorrect by vm.incorrect.collectAsState()
  val timeLeft by vm.timeLeft.collectAsState()
  val colors by vm.colors.collectAsState()
  val score by vm.score.collectAsState()

  AnimateView(
    visible = visible,
    enter = slideInHorizontally(animationSpec = tween(300, 200), initialOffsetX = { width -> -width }),
    exit = slideOutHorizontally(targetOffsetX = { width -> -width })
  ) {
    Card(backgroundColor = colors.background) {
      Column(modifier = Modifier
        .defaultMinSize(minWidth = 120.dp)
        .padding(start = grid2, end = grid2)
      ) {
        Label("Correct:", colors)
        Value(correct, colors.correctText)
        Label("Incorrect:", colors)
        Value(incorrect, colors.incorrectText)
        Label("Score:", colors)
        Value(score, colors.scoreText)
        Label("Time Left:", colors)
        Value(timeLeft, colors.timerText)
      }
    }
  }
}

@Composable
private fun Label(text: String, colors: GameScoreCardColors) {
  Text(text, color = colors.labelText, fontSize = 16.sp)
}
@Composable
private fun Value(text: String, color: Color) {
  CenteredVerticalText(
    modifier = Modifier.padding(start = gridHalf).offset(y = -gridHalf),
    text = text,
    color = color,
    fontSize = 14.sp
  )
}