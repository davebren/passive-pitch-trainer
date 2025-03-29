package org.eski.music.staff

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.eski.music.icons.BasicNote
import org.eski.music.icons.BassClef
import org.eski.music.icons.Flat
import org.eski.music.icons.MusicIcons
import org.eski.music.icons.Sharp
import org.eski.music.icons.TrebleClef
import org.eski.music.model.StaffConfig
import org.eski.ui.util.SizeCascade
import org.eski.ui.util.grid
import kotlin.math.max
import kotlin.math.min


@Composable
fun Staff(
  vm: StaffViewModel,
  config: StaffConfig,
  height: Dp,
  paddingStart: Dp,
  modifier: Modifier = Modifier,
  content: @Composable (() -> Unit)? = null,
) {
  val displayNotes by vm.displayNotes.collectAsState()
  val stackClefs by vm.stackClefs.collectAsState()
  val showQueryBackground by vm.showQueryBackground.collectAsState()
  val colors by vm.colors.collectAsState()

  Card(
    shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp, topEnd = 0.dp, bottomEnd = 0.dp),
    elevation = 16.dp,
    backgroundColor = colors.background,
    modifier = modifier
      .fillMaxWidth()
      .height(height)
      .padding(start = paddingStart)
  ) {
    val trebleClefPainter = rememberVectorPainter(MusicIcons.TrebleClef)
    val bassClefPainter = rememberVectorPainter(MusicIcons.BassClef)
    val sharpPainter = rememberVectorPainter(MusicIcons.Sharp)
    val flatPainter = rememberVectorPainter(MusicIcons.Flat)
    val noteVector = rememberVectorPainter(MusicIcons.BasicNote)

    Canvas(modifier = Modifier.fillMaxSize()) {
      val dimens = StaffDimens(
        config = config,
        height = size.height,
        width = size.width,
        leftMargin = grid.toPx(),
      )

      if (showQueryBackground) drawNoteQueryBackground(dimens, colors)
      drawStaffLines(config, dimens, colors)
      drawStaffClefIcons(trebleClefPainter, bassClefPainter, config, dimens, colors)
      drawKeySignatures(sharpPainter, flatPainter, config, dimens, colors)
      drawNotes(displayNotes, stackClefs, noteVector, dimens, colors)
    }

    content?.invoke()
  }
}

class StaffDimens(
  config: StaffConfig,
  val height: Float,
  val width: Float,
  val leftMargin: Float,
) {
  val verticalMargin = height * (if (config.clefs.size == 1) .38f else .25f)
  val clefHeight = (height - (verticalMargin * 2)) * (if (config.clefs.size == 1) 1f else 0.28f)

  val trebleTopLedgerY: Float = verticalMargin
  val trebleBottomLedgerY: Float = trebleTopLedgerY + clefHeight
  val bassTopLedgerY: Float = (height - verticalMargin) - clefHeight
  val bassBottomLedgerY: Float = bassTopLedgerY + clefHeight
  val ledgerSpacing = clefHeight * .25f

  val signatureStartX: Float = leftMargin + clefHeight

  val sharpHeight = ledgerSpacing * 2.7f
  val sharpWidth = sharpHeight * .37f
  val sharpXInterval = sharpWidth

  val flatHeight = ledgerSpacing * 2.6f
  val flatWidth = flatHeight * .398f
  val flatXInterval = flatWidth * .95f

  val noteStartX = signatureStartX + max(clefHeight, max(flatXInterval * config.key.flats.size, sharpXInterval * config.key.sharps.size))
  val noteHeight = ledgerSpacing * 1.4f
  val noteWidth = noteHeight * 1.289f
  val noteIntervalX = noteWidth * (1.75f * min(1f, width.dp / 300.dp))
}

object StaffConst {
  internal val leftMargin = grid

  val staffHeightSingleClef = SizeCascade(
    listOf(
      SizeCascade.Spec(360.dp, 200.dp),
      SizeCascade.Spec(320.dp, 160.dp),
      SizeCascade.Spec(280.dp, 120.dp),
    )
  )
  val staffHeightDoubleClef = SizeCascade(
    listOf(
      SizeCascade.Spec(400.dp, 240.dp),
      SizeCascade.Spec(360.dp, 200.dp),
      SizeCascade.Spec(320.dp, 160.dp),
      SizeCascade.Spec(280.dp, 120.dp),
    )
  )
}