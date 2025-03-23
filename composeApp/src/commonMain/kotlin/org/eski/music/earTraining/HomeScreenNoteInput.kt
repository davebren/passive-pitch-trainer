package org.eski.music.homeScreen

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.zIndex
import org.eski.music.model.KeySignature
import org.eski.music.ui.noteInputs.PianoInput
import org.eski.ui.animation.AnimateView

@Composable
fun HomeScreenNoteInput(
  keySignature: KeySignature,
  zIndex: Float
) {
  val inputContainerSize = remember { mutableStateOf(IntSize(0, 0)) }

  Column(
    modifier = Modifier.zIndex(zIndex)
      .onSizeChanged { inputContainerSize.value = it }
  ) {
    Spacer(modifier = Modifier.fillMaxHeight().weight(1f))

    AnimateView(
      visible = true,
      enter = slideInVertically(animationSpec = tween(300, 0), initialOffsetY = { height -> height }),
      exit = slideOutVertically(targetOffsetY = { height -> height })
    ) {

      PianoInput(
        containerSize = inputContainerSize.value,
        unnaturalType = keySignature.unnaturalType,
      )
    }
  }
}