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

public val MusicIcons.Flat: ImageVector
  get() {
    if (_flat != null) {
      return _flat!!
    }
    _flat = Builder(
      name = "Flat",
      defaultWidth = 1.0.dp,
      defaultHeight = 2.512.dp,
      viewportWidth = 1.0f,
      viewportHeight = 2.512f
    ).apply {
      path(
        fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveToRelative(0.216f, 1.696f)
        lineToRelative(-0.004f, 0.264f)
        verticalLineToRelative(0.044f)
        quadToRelative(-0.001f, 0.132f, 0.016f, 0.264f)
        curveToRelative(0.18f, -0.152f, 0.372f, -0.32f, 0.372f, -0.556f)
        curveToRelative(0.0f, -0.132f, -0.056f, -0.268f, -0.172f, -0.268f)
        curveToRelative(-0.124f, 0.0f, -0.208f, 0.12f, -0.212f, 0.252f)
        moveToRelative(-0.168f, 0.716f)
        lineTo(0.0f, 0.032f)
        arcTo(0.2f, 0.2f, 0.0f, false, true, 0.108f, 0.0f)
        curveToRelative(0.036f, 0.0f, 0.076f, 0.012f, 0.108f, 0.032f)
        lineToRelative(-0.028f, 1.38f)
        arcToRelative(0.58f, 0.58f, 0.0f, false, true, 0.364f, -0.136f)
        curveToRelative(0.208f, 0.0f, 0.356f, 0.192f, 0.356f, 0.408f)
        curveToRelative(0.0f, 0.32f, -0.344f, 0.468f, -0.588f, 0.676f)
        curveToRelative(-0.06f, 0.052f, -0.096f, 0.152f, -0.18f, 0.152f)
        curveToRelative(-0.052f, 0.0f, -0.092f, -0.044f, -0.092f, -0.1f)
      }
    }
      .build()
    return _flat!!
  }

private var _flat: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = MusicIcons.Flat, contentDescription = null)
  }
}
