package org.eski.music.ui.noteInputs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

const val noteMarginRadians = 0.045f
const val noteMarginRadians2 = 0.09f
const val noteMarginRadians4 = 0.18f

@Composable
fun NotesWheel(modifier: Modifier = Modifier) {
  Card(
//    backgroundColor = AppColors.notesWheelBackground(),
    modifier = modifier
      .widthIn(max = 480.dp)
      .aspectRatio(1f)
      .fillMaxSize()
  ) {
    val shapeA = remember { CircleShape }

    val interactionSource = remember {
      MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val colorAnimated by animateColorAsState(
      targetValue = if (isPressed) Color.Black else Color.DarkGray
    )
    Box(
      modifier = Modifier
        .wrapContentSize(Alignment.Center)
        .padding(8.dp)
        .clip(shapeA)
        .background(colorAnimated)
        .fillMaxSize(.4f)
        .clickable(interactionSource = interactionSource, indication = null) {

        }
    )

    for (i in 0..11) {
      NoteWheelButton(i)
    }
  }
}

@Composable
fun NoteWheelButton(noteIndex: Int) {
  val shape = remember {
    GenericShape() { size, _ ->
      createButtonPath(size.width, noteIndex)
    }
  }
  // Place content inside the shape (optional)
  Box(
    modifier = Modifier
      .fillMaxSize()
//      .padding(gridHalf())
//      .clip(shape)
//      .background(color = Color.Red)
//      .drawWithCache {
//
//        val path = Path().apply {
//          createButtonPath(size.width, noteIndex)
//        }
//        // Cache the drawing
//        onDrawBehind {
//          drawPath(
//            path,
//            color = Color.Black,
//            style = Stroke(
//              width = 2.dp.toPx(),
//              cap = StrokeCap.Round,
////              pathEffect = PathEffect.cornerPathEffect(4.dp.toPx())
//            )
//          )
//        }
//      }
  ) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      drawPath(
        path = Path().apply { createButtonPath(size.width, noteIndex) },
        color = Color.Black,
        style = Stroke(
          width = 6.dp.toPx(),
//          pathEffect = PathEffect.cornerPathEffect(4.dp.toPx())
        )
      )
    }
  }
}

fun Path.createButtonPath(size: Float, noteIndex: Int) {
  val radianOffset = PI.toFloat() / 6f * noteIndex
  val radius = size * .5f
  val innerRadius = radius * .42f
  val bounds = Rect(topLeft = Offset(0f, 0f), bottomRight = Offset(size, size))
  val innerBounds = Rect(
    topLeft = Offset(radius - innerRadius, radius - innerRadius),
    bottomRight = Offset(radius + innerRadius, radius + innerRadius)
  )

  moveTo(
    x = radius + cos(radianOffset + noteMarginRadians) * radius,
    y = radius + sin(radianOffset + noteMarginRadians) * radius
  )
  arcToRad(
    bounds,
    startAngleRadians = radianOffset + noteMarginRadians,
    sweepAngleRadians = (PI.toFloat() / 6f) - noteMarginRadians2,
    forceMoveTo = false
  )
  lineTo(
    x = radius + cos(radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2) * innerRadius,
    y = radius + sin(radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2) * innerRadius,
  )
  arcToRad(
    innerBounds,
    startAngleRadians = radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2,
    sweepAngleRadians = (-PI.toFloat() / 6) + noteMarginRadians4,
    forceMoveTo = false
  )


  close()
}

fun Path.createButtonPath2(size: Float, noteIndex: Int, cornerRadius: Float) {
  val radianOffset = PI.toFloat() / 6f * noteIndex
  val radius = size * .5f
  val innerRadius = radius * .42f
  val bounds = Rect(topLeft = Offset(0f, 0f), bottomRight = Offset(size, size))
  val innerBounds = Rect(
    topLeft = Offset(radius - innerRadius, radius - innerRadius),
    bottomRight = Offset(radius + innerRadius, radius + innerRadius)
  )

  moveTo(
    x = radius + cos(radianOffset + noteMarginRadians) * (radius - cornerRadius),
    y = radius + sin(radianOffset + noteMarginRadians) * (radius - cornerRadius)
  )
  arcToRad(
    bounds,
    startAngleRadians = radianOffset + noteMarginRadians,
    sweepAngleRadians = (PI.toFloat() / 6f) - noteMarginRadians2,
    forceMoveTo = false
  )
  lineTo(
    x = radius + cos(radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2) * innerRadius,
    y = radius + sin(radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2) * innerRadius,
  )
  arcToRad(
    innerBounds,
    startAngleRadians = radianOffset + (PI.toFloat() / 6f) - noteMarginRadians2,
    sweepAngleRadians = (-PI.toFloat() / 6) + noteMarginRadians4,
    forceMoveTo = false
  )

  close()
}
