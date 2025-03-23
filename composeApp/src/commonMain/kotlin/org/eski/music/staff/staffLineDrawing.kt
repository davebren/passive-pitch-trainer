package org.eski.music.staff

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import org.eski.music.model.StaffConfig
import org.eski.ui.util.grid


fun DrawScope.drawStaffLines(
  config: StaffConfig,
  staffDimens: StaffDimens,
  colors: StaffColors,
) {
  for (i in 0..4) {
    drawLedger(
      yPosition = staffDimens.verticalMargin + (i * staffDimens.ledgerSpacing),
      width = staffDimens.width,
      color = colors.staffLines,
    )
  }

  if (config.clefs.size == 2) {
    for (i in 0..4) {
      drawLedger(
        yPosition = staffDimens.height - (staffDimens.verticalMargin + (i * staffDimens.ledgerSpacing)),
        width = staffDimens.width,
        color = colors.staffLines,
      )
    }
  }

  drawRect(
    color = colors.staffLines,
    topLeft = Offset(staffDimens.leftMargin, staffDimens.verticalMargin),
    size = Size(2.dp.toPx(), staffDimens.height - (staffDimens.verticalMargin * 2f))
  )
}

private fun DrawScope.drawLedger(yPosition: Float, width: Float, color: Color) {
  drawRect(
    color = color,
    topLeft = Offset(grid.toPx(), yPosition),
    size = Size(width, 1.5.dp.toPx())
  )
}
