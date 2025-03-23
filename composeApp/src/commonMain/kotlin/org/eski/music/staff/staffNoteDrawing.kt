package org.eski.music.staff

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.unit.dp
import org.eski.music.model.Clef
import org.eski.music.model.Note


fun DrawScope.drawNotes(
  displayNotes: List<StaffNote>,
  stackClefs: Boolean,
  notePainter: VectorPainter,
  staffDimens: StaffDimens,
  colors: StaffColors,
) {
    previewNotes(
      notes = displayNotes,
      notePainter = notePainter,
      staffDimens = staffDimens,
      stackClefs = stackClefs,
      colors = colors,
    )
}

private fun DrawScope.previewNotes(
  notes: List<StaffNote>,
  notePainter: VectorPainter,
  staffDimens: StaffDimens,
  stackClefs: Boolean = false,
  colors: StaffColors,
) {
  var trebleCount = 0
  var bassCount = 0

  notes.forEachIndexed { index, note ->
    val startX = if (stackClefs) {
       when(note.clef) {
         Clef.treble -> staffDimens.noteStartX + (staffDimens.noteIntervalX * trebleCount++)
         Clef.bass -> staffDimens.noteStartX + (staffDimens.noteIntervalX * bassCount++)
       }
    } else staffDimens.noteStartX + (staffDimens.noteIntervalX * index)

    if (startX > staffDimens.width) return@forEachIndexed

    if (note.clef == Clef.treble) {
      drawTrebleNote(
        note = note.note,
        staffDimens = staffDimens,
        notePainter = notePainter,
        startX = startX,
        noteColor = colors.note,
        ledgerColor = colors.noteLedger,
      )
    }
    else if (note.clef == Clef.bass) {
      drawBassNote(
        note.note,
        staffDimens,
        notePainter,
        startX,
        noteColor = colors.note,
        ledgerColor = colors.noteLedger,
      )
    }
  }
}

private fun DrawScope.drawTrebleNote(
  note: Note,
  staffDimens: StaffDimens,
  notePainter: VectorPainter,
  startX: Float,
  noteColor: Color,
  ledgerColor: Color,
) {
  val noteTopY = (staffDimens.trebleTopLedgerY + (staffDimens.ledgerSpacing * ((note.clefIndex - 9.5f) * -.5f)))

  val extraTopLedgerLines = ((note.clefIndex - Note.f5.clefIndex) / 2).toInt()
  val extraBottomLedgerLines = ((Note.e4.clefIndex - note.clefIndex) / 2).toInt()
  val extraLedgerStartX = startX - (staffDimens.noteWidth * .3f)

  for (i in 1..extraTopLedgerLines) {
    drawRect(
      color = ledgerColor,
      topLeft = Offset(extraLedgerStartX, staffDimens.trebleTopLedgerY - (i * staffDimens.ledgerSpacing)),
      size = Size(staffDimens.noteWidth  * 1.6f, 1.5.dp.toPx())
    )
  }

  for (i in 1..extraBottomLedgerLines) {
    drawRect(
      color = ledgerColor,
      topLeft = Offset(extraLedgerStartX, staffDimens.trebleBottomLedgerY + (i * staffDimens.ledgerSpacing)),
      size = Size(staffDimens.noteWidth  * 1.6f, 1.5.dp.toPx())
    )
  }

  translate(
    left = startX,
    top = noteTopY - ((staffDimens.noteHeight - staffDimens.ledgerSpacing) * .7f)
  ) {
    with(notePainter) {
      draw(
        size = Size(staffDimens.noteWidth, staffDimens.noteHeight),
        alpha = 1f,
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(noteColor)
      )
    }
  }
}

private fun DrawScope.drawBassNote(
  note: Note,
  staffDimens: StaffDimens,
  notePainter: VectorPainter,
  startX: Float,
  noteColor: Color,
  ledgerColor: Color,
) {
  val noteTopY = (staffDimens.bassTopLedgerY + (staffDimens.ledgerSpacing * ((note.clefIndex - -2.5f) * -.5f)))

  val extraTopLedgerLines = ((note.clefIndex - Note.a3.clefIndex) / 2).toInt()
  val extraBottomLedgerLines = ((Note.g2.clefIndex - note.clefIndex) / 2).toInt()
  val extraLedgerStartX = startX - (staffDimens.noteWidth * .3f)

  for (i in 1..extraTopLedgerLines) {
    drawRect(
      color = ledgerColor,
      topLeft = Offset(extraLedgerStartX, staffDimens.bassTopLedgerY - (i * staffDimens.ledgerSpacing)),
      size = Size(staffDimens.noteWidth  * 1.6f, 1.5.dp.toPx())
    )
  }

  for (i in 1..extraBottomLedgerLines) {
    drawRect(
      color = ledgerColor,
      topLeft = Offset(extraLedgerStartX, staffDimens.bassBottomLedgerY + (i * staffDimens.ledgerSpacing)),
      size = Size(staffDimens.noteWidth  * 1.6f, 1.5.dp.toPx())
    )
  }

  translate(
    left = startX,
    top = noteTopY - ((staffDimens.noteHeight - staffDimens.ledgerSpacing) * .7f)
  ) {
    with(notePainter) {
      draw(
        size = Size(staffDimens.noteWidth, staffDimens.noteHeight),
        alpha = 1f,
        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(noteColor)
      )
    }
  }
}
