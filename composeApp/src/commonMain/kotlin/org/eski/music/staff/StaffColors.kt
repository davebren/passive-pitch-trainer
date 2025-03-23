package org.eski.music.staff

import androidx.compose.ui.graphics.Color

data class StaffColors(
  val background: Color = Color.White,
  val staffLines: Color = Color.DarkGray,
  val clefs: Color = staffLines,
  val trebleClef: Color = clefs,
  val bassClef: Color = clefs,
  val keySignature: Color = staffLines,
  val note: Color = staffLines,
  val noteLedger: Color = staffLines,
  val noteQueryBackground: Color = staffLines,
)
