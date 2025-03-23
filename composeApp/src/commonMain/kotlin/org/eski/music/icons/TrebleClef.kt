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

public val MusicIcons.TrebleClef: ImageVector
  get() {
    if (_trebleClef != null) {
      return _trebleClef!!
    }
    _trebleClef = Builder(
      name = "TrebleClef",
      defaultWidth = 78.53.dp,
      defaultHeight = 223.66.dp,
      viewportWidth = 78.54f,
      viewportHeight = 223.66f
    ).apply {
      path(
        fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveTo(41.02f, 172.1f)
        curveToRelative(-11.5f, -0.67f, -23.24f, -7.88f, -30.25f, -16.16f)
        curveToRelative(-8.14f, -9.6f, -11.49f, -22.64f, -10.64f, -33.36f)
        curveToRelative(2.53f, -31.48f, 36.76f, -50.09f, 49.43f, -63.84f)
        curveToRelative(8.48f, -9.2f, 10.26f, -13.69f, 12.15f, -18.42f)
        curveToRelative(3.67f, -9.16f, 4.26f, -19.9f, -3.01f, -20.7f)
        curveToRelative(-6.94f, -0.76f, -12.65f, 10.15f, -15.21f, 17.62f)
        curveToRelative(-2.29f, 6.71f, -3.82f, 13.45f, -2.44f, 23.56f)
        curveToRelative(0.62f, 4.55f, 17.69f, 131.4f, 17.98f, 133.37f)
        curveToRelative(2.88f, 19.65f, -8.36f, 27.78f, -19.83f, 29.23f)
        curveToRelative(-24.77f, 3.12f, -32.72f, -22.05f, -21.62f, -31.61f)
        curveToRelative(8.55f, -7.36f, 20.99f, -1.04f, 20.31f, 11.06f)
        curveToRelative(-0.6f, 10.72f, -11.11f, 11.04f, -13.79f, 10.79f)
        curveToRelative(4.12f, 7.39f, 35.21f, 11.26f, 30.43f, -22.15f)
        curveToRelative(-0.67f, -4.69f, -16.45f, -123.91f, -16.83f, -126.58f)
        curveToRelative(-2.89f, -19.97f, -3.34f, -35.78f, 7.07f, -54.23f)
        curveTo(48.62f, 3.87f, 54.69f, -0.52f, 57.67f, 0.05f)
        curveToRelative(0.65f, 0.13f, 1.31f, 0.37f, 1.83f, 0.9f)
        curveToRelative(7.98f, 8.23f, 10.43f, 26.46f, 9.56f, 36.94f)
        curveToRelative(-0.89f, 10.73f, -1.39f, 21.83f, -12.06f, 34.78f)
        curveToRelative(-4.13f, 5.01f, -16.72f, 16.05f, -23.95f, 22.39f)
        curveToRelative(-10.15f, 8.91f, -17.51f, 16.69f, -21.49f, 26.08f)
        curveToRelative(-4.47f, 10.55f, -5.23f, 23.84f, 4.88f, 35.63f)
        curveToRelative(5.82f, 6.66f, 15.46f, 11.39f, 23.58f, 11.48f)
        curveToRelative(22.58f, 0.28f, 29.17f, -10.85f, 29.38f, -23.45f)
        curveToRelative(0.35f, -20.75f, -24.71f, -28.6f, -35.01f, -14.33f)
        curveToRelative(-5.97f, 8.27f, -3.35f, 16.98f, 0.66f, 21.13f)
        curveToRelative(1.38f, 1.42f, 2.91f, 2.51f, 4.37f, 3.11f)
        curveToRelative(0.53f, 0.22f, 1.77f, 0.8f, 1.41f, 1.64f)
        curveToRelative(-0.29f, 0.7f, -0.86f, 0.74f, -1.38f, 0.68f)
        curveToRelative(-6.49f, -0.78f, -13.69f, -6.99f, -15.46f, -17.67f)
        curveToRelative(-2.58f, -15.56f, 11.16f, -34.03f, 30.76f, -31.01f)
        curveToRelative(12.84f, 1.98f, 24.75f, 13.02f, 23.71f, 33.59f)
        curveToRelative(-0.9f, 17.63f, -15.51f, 31.42f, -37.45f, 30.15f)
      }
    }
      .build()
    return _trebleClef!!
  }

private var _trebleClef: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = MusicIcons.TrebleClef, contentDescription = null)
  }
}
