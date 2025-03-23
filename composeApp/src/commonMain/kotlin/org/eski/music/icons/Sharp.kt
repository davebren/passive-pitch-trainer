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

public val MusicIcons.Sharp: ImageVector
  get() {
    if (_sharp != null) {
      return _sharp!!
    }
    _sharp = Builder(
      name = "Sharp",
      defaultWidth = 1.933.dp,
      defaultHeight = 5.2719.dp,
      viewportWidth = 1.1f,
      viewportHeight = 3.0f
    ).apply {
      path(
        fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveTo(0.864f, 2.748f)
        curveToRelative(0.0f, 0.04f, -0.032f, 0.076f, -0.072f, 0.076f)
        arcToRelative(0.08f, 0.08f, 0.0f, false, true, -0.076f, -0.076f)
        verticalLineToRelative(-0.58f)
        lineToRelative(-0.332f, 0.124f)
        verticalLineToRelative(0.632f)
        curveToRelative(0.0f, 0.04f, -0.036f, 0.076f, -0.076f, 0.076f)
        reflectiveCurveToRelative(-0.072f, -0.036f, -0.072f, -0.076f)
        verticalLineToRelative(-0.58f)
        lineToRelative(-0.128f, 0.048f)
        quadToRelative(-0.014f, 0.005f, -0.028f, 0.004f)
        arcToRelative(0.08f, 0.08f, 0.0f, false, true, -0.08f, -0.08f)
        verticalLineToRelative(-0.24f)
        curveTo(0.0f, 2.044f, 0.02f, 2.012f, 0.052f, 2.0f)
        lineToRelative(0.184f, -0.064f)
        verticalLineToRelative(-0.64f)
        lineToRelative(-0.128f, 0.044f)
        quadToRelative(-0.014f, 0.005f, -0.028f, 0.004f)
        arcToRelative(0.08f, 0.08f, 0.0f, false, true, -0.08f, -0.08f)
        verticalLineToRelative(-0.24f)
        curveTo(0.0f, 0.992f, 0.02f, 0.964f, 0.052f, 0.952f)
        lineTo(0.236f, 0.884f)
        verticalLineTo(0.252f)
        curveToRelative(0.0f, -0.04f, 0.032f, -0.076f, 0.072f, -0.076f)
        reflectiveCurveToRelative(0.076f, 0.036f, 0.076f, 0.076f)
        verticalLineToRelative(0.58f)
        lineTo(0.716f, 0.708f)
        verticalLineTo(0.076f)
        curveTo(0.716f, 0.036f, 0.752f, 0.0f, 0.792f, 0.0f)
        reflectiveCurveToRelative(0.072f, 0.036f, 0.072f, 0.076f)
        verticalLineToRelative(0.58f)
        lineTo(0.992f, 0.608f)
        curveTo(1.0f, 0.604f, 1.012f, 0.604f, 1.02f, 0.604f)
        arcToRelative(0.08f, 0.08f, 0.0f, false, true, 0.08f, 0.08f)
        verticalLineToRelative(0.24f)
        curveToRelative(0.0f, 0.032f, -0.02f, 0.064f, -0.052f, 0.076f)
        lineToRelative(-0.184f, 0.064f)
        verticalLineToRelative(0.64f)
        lineToRelative(0.128f, -0.044f)
        curveToRelative(0.008f, -0.004f, 0.02f, -0.004f, 0.028f, -0.004f)
        arcToRelative(0.08f, 0.08f, 0.0f, false, true, 0.08f, 0.08f)
        verticalLineToRelative(0.24f)
        curveToRelative(0.0f, 0.032f, -0.02f, 0.06f, -0.052f, 0.072f)
        lineToRelative(-0.184f, 0.068f)
        close()
        moveTo(0.384f, 1.24f)
        verticalLineToRelative(0.64f)
        lineToRelative(0.332f, -0.12f)
        verticalLineToRelative(-0.64f)
        close()
      }
    }
      .build()
    return _sharp!!
  }

private var _sharp: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = MusicIcons.Sharp, contentDescription = null)
  }
}
