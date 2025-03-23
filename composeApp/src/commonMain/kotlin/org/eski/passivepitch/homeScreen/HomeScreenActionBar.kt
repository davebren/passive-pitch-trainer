package org.eski.passivepitch.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.util.grid6
import org.eski.ui.util.gridHalf
import org.eski.ui.views.spacer

@Composable
fun ActionBarMenu(
  optionsVm: HomeScreenOptionsViewModel,
  heightState: MutableState<Dp>,
) {
  val heightPixels = remember { mutableStateOf(0) }
  heightState.value = with(LocalDensity.current) { heightPixels.value.toDp() }

  Box(modifier = Modifier.fillMaxWidth()
    .onSizeChanged { heightPixels.value = it.height }
  ) {
    LeftButtons(optionsVm, Modifier.align(alignment = Alignment.CenterStart))
    Column(Modifier.align(alignment = Alignment.Center)) {
      spacer(grid2)
      Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
        Icon(
          modifier = Modifier.align(alignment = Alignment.CenterVertically).size(12.dp),
          imageVector = Icons.Filled.MusicNote, contentDescription = "",
          tint = Color.Red
        )
        spacer(width = grid)
        Text(
          text = "PassivePitch",
          fontSize = 24.sp,
          color = Color.White,
        )
        spacer(width = grid)
        Icon(
          modifier = Modifier.align(alignment = Alignment.CenterVertically).size(12.dp),
          imageVector = Icons.Filled.MusicNote, contentDescription = "",
          tint = Color.Red
        )
      }
      Text(
        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
        text = "by David Breneisen",
        fontSize = 12.sp,
        color = Color.LightGray,
      )
    }

    RightButtons(optionsVm, Modifier.align(alignment = Alignment.CenterEnd))
  }
}

@Composable private fun LeftButtons(optionsVm: HomeScreenOptionsViewModel, modifier: Modifier) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = gridHalf),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(
      modifier = Modifier
        .size(grid6)
        .padding(gridHalf),
      onClick = { optionsVm.introClicked() }
    ) {
      Icon(
        imageVector = Icons.Default.Info,
        contentDescription = "PassivePitch Information",
        tint = Color.White,
      )
    }
  }
}

@Composable private fun RightButtons(optionsVm: HomeScreenOptionsViewModel, modifier: Modifier) {
  Row(
    modifier = modifier
      .padding(horizontal = gridHalf)
  ) {
//
//    IconButton(
//      modifier = Modifier
//        .size(grid6)
//        .padding(gridHalf),
//      onClick = { optionsVm.achievementsClicked() }
//    ) {
//      Icon(
//        imageVector = Icons.Default.EmojiEvents,
//        contentDescription = "Achievements",
//        tint = Color.White,
//      )
//    }

    IconButton(
      modifier = Modifier
        .size(grid6)
        .padding(gridHalf),
      onClick = { optionsVm.settingsClicked() }
    ) {
      Icon(
        imageVector = Icons.Default.Settings,
        contentDescription = "Settings",
        tint = Color.White,
      )
    }
  }
}