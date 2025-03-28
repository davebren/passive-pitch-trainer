package org.eski.game.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.eski.ui.animation.AnimateView
import org.eski.ui.util.grid
import org.eski.ui.util.grid2

@Composable
fun HighScoreCard(
  visible: Boolean,
  score: String,
  background: Color = Color.White,
  textColor: Color = Color.DarkGray,
  roundedCornerShape: RoundedCornerShape = RoundedCornerShape(4.dp)
) {
  AnimateView(
    visible = visible,
    enter = slideInHorizontally(animationSpec = tween(300, 200), initialOffsetX = { width -> -width }),
    exit = slideOutHorizontally(targetOffsetX = { width -> -width })
  ) {
    Card(backgroundColor = background, shape = roundedCornerShape) {
      Column(
        modifier = Modifier
          .padding(start = grid2, end = grid2, top = grid, bottom = grid)
          .defaultMinSize(minWidth = 120.dp)
      ) {
        Text(
          modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
          text = "High Score",
          color = textColor,
          fontSize = 16.sp,
        )
        Text(
          modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
          text = score,
          color = textColor,
          fontSize = 14.sp,
        )
      }
    }
  }
}