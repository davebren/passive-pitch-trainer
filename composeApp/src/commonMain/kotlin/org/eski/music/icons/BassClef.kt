package org.eski.music.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

public val MusicIcons.BassClef: ImageVector
  get() {
    if (_bassClef != null) {
      return _bassClef!!
    }
    _bassClef = Builder(
      name = "BassClef",
      defaultWidth = 671.0.dp,
      defaultHeight = 772.0.dp,
      viewportWidth = 671.0f,
      viewportHeight = 772.0f
    ).apply {
      path(
        fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveTo(567.0f, 386.0f)
        curveToRelative(0.0f, -29.0f, 23.0f, -52.0f, 52.0f, -52.0f)
        reflectiveCurveToRelative(52.0f, 23.0f, 52.0f, 52.0f)
        reflectiveCurveToRelative(-23.0f, 52.0f, -52.0f, 52.0f)
        reflectiveCurveToRelative(-52.0f, -23.0f, -52.0f, -52.0f)
        moveToRelative(0.0f, -250.0f)
        curveToRelative(0.0f, -29.0f, 23.0f, -52.0f, 52.0f, -52.0f)
        reflectiveCurveToRelative(52.0f, 23.0f, 52.0f, 52.0f)
        reflectiveCurveToRelative(-23.0f, 52.0f, -52.0f, 52.0f)
        reflectiveCurveToRelative(-52.0f, -23.0f, -52.0f, -52.0f)
        moveTo(244.0f, 0.0f)
        curveToRelative(171.0f, 0.0f, 292.0f, 86.0f, 292.0f, 248.0f)
        curveToRelative(0.0f, 263.0f, -264.0f, 415.0f, -517.0f, 521.0f)
        curveToRelative(-2.0f, 2.0f, -5.0f, 3.0f, -8.0f, 3.0f)
        curveToRelative(-6.0f, 0.0f, -11.0f, -5.0f, -11.0f, -11.0f)
        curveToRelative(0.0f, -3.0f, 1.0f, -6.0f, 3.0f, -8.0f)
        curveToRelative(203.0f, -118.0f, 415.0f, -265.0f, 415.0f, -494.0f)
        curveToRelative(0.0f, -121.0f, -64.0f, -237.0f, -174.0f, -237.0f)
        curveToRelative(-79.0f, 0.0f, -138.0f, 57.0f, -164.0f, 133.0f)
        curveToRelative(14.0f, -8.0f, 28.0f, -13.0f, 43.0f, -13.0f)
        curveToRelative(55.0f, 0.0f, 100.0f, 45.0f, 100.0f, 100.0f)
        curveToRelative(0.0f, 58.0f, -44.0f, 107.0f, -100.0f, 107.0f)
        curveToRelative(-60.0f, 0.0f, -112.0f, -48.0f, -112.0f, -107.0f)
        curveTo(11.0f, 110.0f, 114.0f, 0.0f, 244.0f, 0.0f)
      }
    }
      .build()
    return _bassClef!!
  }

private var _bassClef: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = MusicIcons.BassClef, contentDescription = null)
  }
}
