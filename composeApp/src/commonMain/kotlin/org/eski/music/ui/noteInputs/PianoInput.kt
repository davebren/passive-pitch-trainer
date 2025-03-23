package org.eski.music.ui.noteInputs

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CancellationException
import org.eski.music.model.Note
import org.eski.ui.AppColors
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.views.text.CenteredVerticalText
import org.eski.ui.views.PressExpandButtonState
import org.eski.ui.views.PressExpandButtonState.expanded
import org.eski.ui.views.PressExpandButtonState.pressed
import org.eski.ui.views.PressExpandButtonState.unpressed


@Composable
fun PianoInput(
  containerSize: IntSize? = null,
  modifier: Modifier = Modifier,
  unnaturalType: Note.UnnaturalType = Note.UnnaturalType.sharp
) {
  val whiteKeySize = remember { mutableStateOf(IntSize.Zero) }
  val whiteKeySizeDp = mutableStateOf(with(LocalDensity.current) { DpSize(
    width = whiteKeySize.value.width.toDp(),
    height = whiteKeySize.value.height.toDp())
  })
  val blackKeySizeDp = mutableStateOf(DpSize(whiteKeySizeDp.value.width * .75f, whiteKeySizeDp.value.height * .5f))

  val horizontalPadding =
    if (with(LocalDensity.current) { containerSize?.width?.toDp()?.let { it > 400.dp } != false }) grid2
    else 0.dp

  val verticalPadding =
    if (with(LocalDensity.current) { containerSize?.height?.toDp()?.let { it > 400.dp } != false }) grid
    else 0.dp

  val height =
    if (with(LocalDensity.current) { containerSize?.height?.toDp()?.let { it > 400.dp } != false }) 120.dp
    else 104.dp

  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(height)
      .padding(
        vertical = verticalPadding,
        horizontal = horizontalPadding
      )
      .zIndex(2f)
  ) {
    Row(
      modifier = Modifier.fillMaxSize()
    ) {
      octave4WhiteKeyNotes.forEach { note ->
        WhitePianoKey(
          note = note,
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
          sizeState = whiteKeySize,
          sizeStateDp = whiteKeySizeDp,
          shape = when(note) {
            octave4WhiteKeyNotes.first() -> startCornerShape
            octave4BlackKeyNotes.last() -> endCornerShape
            else -> cornerShape
          }
        )
      }
    }

    octave4BlackKeyNotes.forEachIndexed { index, note ->
      val centerX = whiteKeySizeDp.value.width * when(index) {
        0 -> 1f
        1 -> 2f
        2 -> 4f
        3 -> 5f
        4 -> 6f
        else -> 0f
      }

      BlackPianoKey(
        note = note,
        unnaturalType = unnaturalType,
        sizeStateDp = blackKeySizeDp,
        modifier = Modifier
          .offset(x = centerX - blackKeySizeDp.value.width * .5f)
          .height(blackKeySizeDp.value.height)
          .width(blackKeySizeDp.value.width)
      )
    }
  }
}

@Composable
fun WhitePianoKey(
  note: Note,
  modifier: Modifier,
  shape: RoundedCornerShape,
  sizeState: MutableState<IntSize>,
  sizeStateDp: MutableState<DpSize>,
) {
  Card(
    elevation = 32.dp,
    shape = shape,
    backgroundColor = AppColors.pianoWhiteKeyBackground(),
    border = BorderStroke(2.dp, color = AppColors.pianoWhiteKeyBorder()),
    modifier = modifier
      .fillMaxHeight()
      .onSizeChanged { sizeState.value = it }
      .clickable {
        NoteInputs.noteClickedFlow.tryEmit(note)
      }
  ) {
    CenteredVerticalText(
      text = note.nameo,
      fontSize = 22.sp,
      color = AppColors.pianoWhiteKeyText(),
      modifier = Modifier.offset(y = sizeStateDp.value.height * .2f),
    )
  }
}

@Composable
fun BlackPianoKey(
  note: Note,
  unnaturalType: Note.UnnaturalType,
  modifier: Modifier,
  sizeStateDp: MutableState<DpSize>,
) {
  Card(
    elevation = 16.dp,
    backgroundColor = AppColors.pianoBlackKeyBackground(),
    border = BorderStroke(3.dp, color = AppColors.pianoBlackKeyBorder()),
    modifier = modifier.clickable {
      NoteInputs.noteClickedFlow.tryEmit(note)
    }
  ) {
    CenteredVerticalText(
      text = note.name(unnaturalType),
      fontSize = 18.sp,
      color = AppColors.pianoBlackKeyText(),
      modifier = Modifier.offset(y = sizeStateDp.value.height * .2f),
    )
  }
}

private val octave4WhiteKeyNotes = listOf(
  Note.c4,
  Note.d4,
  Note.e4,
  Note.f4,
  Note.g4,
  Note.a4,
  Note.b4,
)

private val octave4BlackKeyNotes = listOf(
  Note.cs4,
  Note.ds4,
  Note.fs4,
  Note.gs4,
  Note.as4,
)

private val cornerShape = RoundedCornerShape(
  topStart =  0.dp,
  topEnd = 0.dp,
  bottomStart = 8.dp,
  bottomEnd = 8.dp
)

private val startCornerShape = RoundedCornerShape(
  topStart =  CornerSize(2.dp),
  topEnd = cornerShape.topEnd,
  bottomStart = cornerShape.bottomStart,
  bottomEnd = cornerShape.bottomEnd,
)

private val endCornerShape = RoundedCornerShape(
  topStart = cornerShape.topStart,
  topEnd = CornerSize(2.dp),
  bottomStart = cornerShape.bottomStart,
  bottomEnd = cornerShape.bottomEnd,
)

private fun pointerBlock(
  buttonState: MutableTransitionState<PressExpandButtonState>,
  expandedState: MutableState<Boolean>? = null,
): suspend PointerInputScope.() -> Unit = {
  detectTapGestures(
    onPress = {
      try {
        if (buttonState.currentState == unpressed) {
          buttonState.targetState = pressed
        }

        awaitRelease()
        if (buttonState.currentState == pressed || buttonState.targetState == pressed) {
          buttonState.targetState = expanded
          expandedState?.value = true
        } else if (buttonState.currentState == expanded || buttonState.targetState == expanded) {
          if (expandedState == null) buttonState.targetState = unpressed
        }
      } catch (cancel: CancellationException) {
        if (buttonState.currentState == pressed || buttonState.targetState == pressed) {
          buttonState.targetState = unpressed
        }
      }
    },
  )
}