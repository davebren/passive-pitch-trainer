package org.eski.music.staff

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import org.eski.music.model.Clef
import org.eski.music.model.NoteLetter
import org.eski.music.model.StaffConfig


fun DrawScope.drawKeySignatures(
  sharpPainter: VectorPainter,
  flatPainter: VectorPainter,
  config: StaffConfig,
  staffDimens: StaffDimens,
  colors: StaffColors,
) {
  when(config.clefs) {
    Clef.Combo.treble, Clef.Combo.trebleAndBass -> {
      drawTrebleKeySignature(sharpPainter, flatPainter, config, staffDimens, colors.keySignature)
    }
    Clef.Combo.bass -> {}
  }
  when(config.clefs) {
    Clef.Combo.bass, Clef.Combo.trebleAndBass -> {
      drawBassKeySignature(sharpPainter, flatPainter, config, staffDimens, colors.keySignature)
    }
    Clef.Combo.treble -> {}
  }
}

fun DrawScope.drawTrebleKeySignature(
  sharpPainter: VectorPainter,
  flatPainter: VectorPainter,
  config: StaffConfig,
  staffDimens: StaffDimens,
  color: Color,
) {
  val sharpFPosY = staffDimens.trebleTopLedgerY + staffDimens.sharpHeight * -.5f

  config.key.sharps.forEachIndexed { index, it ->
    val yPos = sharpFPosY + staffDimens.ledgerSpacing * when (it) {
      NoteLetter.A -> 2.5f
      NoteLetter.B -> 2f
      NoteLetter.C -> 1.5f
      NoteLetter.D -> 1f
      NoteLetter.E -> .5f
      NoteLetter.F -> 0f
      NoteLetter.G -> -.5f
    }
    val xPos = staffDimens.signatureStartX + staffDimens.sharpXInterval * index

    translate(left = xPos, top = yPos) {
      with(sharpPainter) {
        draw(
          size = Size(staffDimens.sharpWidth, staffDimens.sharpHeight),
          alpha = 1f,
          colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
        )
      }
    }
  }

  val flatFPosY = staffDimens.trebleTopLedgerY + staffDimens.flatHeight * -.7f

  config.key.flats.forEachIndexed { index, it ->
    val yPos = flatFPosY + staffDimens.ledgerSpacing * when (it) {
      NoteLetter.A -> 2.5f
      NoteLetter.B -> 2f
      NoteLetter.C -> 1.5f
      NoteLetter.D -> 1f
      NoteLetter.E -> .5f
      NoteLetter.F -> 3.5f
      NoteLetter.G -> 3f
    }
    val xPos = staffDimens.signatureStartX + staffDimens.flatXInterval * index

    translate(left = xPos, top = yPos) {
      with(flatPainter) {
        draw(
          size = Size(staffDimens.flatWidth, staffDimens.flatHeight),
          alpha = 1f,
          colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
        )
      }
    }
  }
}

fun DrawScope.drawBassKeySignature(
  sharpPainter: VectorPainter,
  flatPainter: VectorPainter,
  config: StaffConfig,
  staffDimens: StaffDimens,
  color: Color,
) {
  val sharpAPosY = staffDimens.bassTopLedgerY - staffDimens.sharpHeight * .5f

  config.key.sharps.forEachIndexed { index, it ->
    val yPos = sharpAPosY + staffDimens.ledgerSpacing * when (it) {
      NoteLetter.A -> 3.5f
      NoteLetter.B -> 3f
      NoteLetter.C -> 2.5f
      NoteLetter.D -> 2f
      NoteLetter.E -> 1.5f
      NoteLetter.F -> 1f
      NoteLetter.G -> .5f
    }
    val xPos = staffDimens.signatureStartX + staffDimens.sharpXInterval * index

    translate(left = xPos, top = yPos) {
      with(sharpPainter) {
        draw(
          size = Size(staffDimens.sharpWidth, staffDimens.sharpHeight),
          alpha = 1f,
          colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
        )
      }
    }
  }
  val flatFPosY = staffDimens.bassTopLedgerY + staffDimens.flatHeight * -.7f

  config.key.flats.forEachIndexed { index, it ->
    val yPos = flatFPosY + staffDimens.ledgerSpacing * when (it) {
      NoteLetter.A -> 3.5f
      NoteLetter.B -> 3f
      NoteLetter.C -> 2.5f
      NoteLetter.D -> 2f
      NoteLetter.E -> 1.5f
      NoteLetter.F -> 4.5f
      NoteLetter.G -> 4f
    }
    val xPos = staffDimens.signatureStartX + staffDimens.flatXInterval * index

    translate(left = xPos, top = yPos) {
      with(flatPainter) {
        draw(
          size = Size(staffDimens.flatWidth, staffDimens.flatHeight),
          alpha = 1f,
          colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color)
        )
      }
    }
  }
}