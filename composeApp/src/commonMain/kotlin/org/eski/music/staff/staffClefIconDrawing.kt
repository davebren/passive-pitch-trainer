package org.eski.music.staff

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import org.eski.music.model.Clef
import org.eski.music.model.StaffConfig
import org.eski.music.staff.StaffConst.leftMargin
import org.eski.ui.util.grid

fun DrawScope.drawStaffClefIcons(
  trebleClefPainter: VectorPainter,
  bassClefPainter: VectorPainter,
  config: StaffConfig,
  staffDimens: StaffDimens,
  colors: StaffColors,
) {
  when(config.clefs) {
    Clef.Combo.treble, Clef.Combo.trebleAndBass -> {
      drawTrebleClefIcon(
        painter = trebleClefPainter,
        staffDimens = staffDimens,
        color = colors.trebleClef,
      )
    }
    Clef.Combo.bass -> {}
  }

  when(config.clefs) {
    Clef.Combo.bass, Clef.Combo.trebleAndBass -> {
      drawBassClefIcon(
        painter = bassClefPainter,
        staffDimens = staffDimens,
        color = colors.bassClef,
      )
    }
    Clef.Combo.treble -> {}
  }
}


private fun DrawScope.drawTrebleClefIcon(
  painter: VectorPainter,
  staffDimens: StaffDimens,
  color: Color,
) {
  val height = staffDimens.clefHeight * 1.6f
  translate(
    left = staffDimens.leftMargin + grid.toPx(),
    top = staffDimens.trebleTopLedgerY - (height * .14f)
  ) {
    with(painter) {
      draw(
        size = Size(height * .44f, height),
        alpha = 1f,
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
      )
    }
  }
}

private fun DrawScope.drawBassClefIcon(
  painter: VectorPainter,
  staffDimens: StaffDimens,
  color: Color,
) {
  val height = staffDimens.clefHeight * .8f
  translate(left = (leftMargin + grid).toPx(), top = staffDimens.bassTopLedgerY + (height * .13f)) {
    with(painter) {
      draw(
        size = Size(height * .83f, height),
        alpha = 1f,
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
      )
    }
  }
}