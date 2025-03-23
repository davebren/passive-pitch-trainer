package org.eski.music.staff

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawNoteQueryBackground(
  staffDimens: StaffDimens,
  staffColors: StaffColors,
) {
  drawRect(
    color = staffColors.noteQueryBackground,
    topLeft = Offset(
      x = staffDimens.noteStartX - (staffDimens.noteWidth * .5f),
      y = 0f
    ),
    size = Size(
      width = staffDimens.noteWidth * 2f,
      height = staffDimens.height,
    )
  )
}