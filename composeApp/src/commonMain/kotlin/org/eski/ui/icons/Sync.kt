package org.eski.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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

public val Icons.Sync: ImageVector
  get() {
    if (_sync != null) {
      return _sync!!
    }
    _sync = Builder(
      name = "Sync",
      defaultWidth = 24.0.dp,
      defaultHeight = 24.0.dp,
      viewportWidth = 960.0f,
      viewportHeight = 960.0f
    ).apply {
      path(
        fill = SolidColor(Color(0xFFe8eaed)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveTo(160.0f, 800.0f)
        verticalLineToRelative(-80.0f)
        horizontalLineToRelative(110.0f)
        lineToRelative(-16.0f, -14.0f)
        quadToRelative(-52.0f, -46.0f, -73.0f, -105.0f)
        reflectiveQuadToRelative(-21.0f, -119.0f)
        quadToRelative(0.0f, -111.0f, 66.5f, -197.5f)
        reflectiveQuadTo(400.0f, 170.0f)
        verticalLineToRelative(84.0f)
        quadToRelative(-72.0f, 26.0f, -116.0f, 88.5f)
        reflectiveQuadTo(240.0f, 482.0f)
        quadToRelative(0.0f, 45.0f, 17.0f, 87.5f)
        reflectiveQuadToRelative(53.0f, 78.5f)
        lineToRelative(10.0f, 10.0f)
        verticalLineToRelative(-98.0f)
        horizontalLineToRelative(80.0f)
        verticalLineToRelative(240.0f)
        lineTo(160.0f, 800.0f)
        close()
        moveTo(560.0f, 790.0f)
        verticalLineToRelative(-84.0f)
        quadToRelative(72.0f, -26.0f, 116.0f, -88.5f)
        reflectiveQuadTo(720.0f, 478.0f)
        quadToRelative(0.0f, -45.0f, -17.0f, -87.5f)
        reflectiveQuadTo(650.0f, 312.0f)
        lineToRelative(-10.0f, -10.0f)
        verticalLineToRelative(98.0f)
        horizontalLineToRelative(-80.0f)
        verticalLineToRelative(-240.0f)
        horizontalLineToRelative(240.0f)
        verticalLineToRelative(80.0f)
        lineTo(690.0f, 240.0f)
        lineToRelative(16.0f, 14.0f)
        quadToRelative(49.0f, 49.0f, 71.5f, 106.5f)
        reflectiveQuadTo(800.0f, 478.0f)
        quadToRelative(0.0f, 111.0f, -66.5f, 197.5f)
        reflectiveQuadTo(560.0f, 790.0f)
        close()
      }
    }
      .build()
    return _sync!!
  }

private var _sync: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = Icons.Sync, contentDescription = null)
  }
}
