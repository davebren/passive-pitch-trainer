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

public val MusicIcons.BasicNote: ImageVector
  get() {
    if (_basicnote != null) {
      return _basicnote!!
    }
    _basicnote = Builder(
      name = "Basicnote",
      defaultWidth = 347.84.dp,
      defaultHeight = 269.773.dp,
      viewportWidth = 92.033f,
      viewportHeight = 71.378f
    ).apply {
      path(
        fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
        pathFillType = NonZero
      ) {
        moveTo(0.019f, 47.937f)
        arcToRelative(32.047f, 48.623f, 64.564f, true, false, 91.995f, -24.495f)
        arcToRelative(32.047f, 48.623f, 64.564f, true, false, -91.995f, 24.495f)
        close()
      }
    }
      .build()
    return _basicnote!!
  }

private var _basicnote: ImageVector? = null

@Preview
@Composable
private fun Preview() {
  Box(modifier = Modifier.padding(12.dp)) {
    Image(imageVector = MusicIcons.BasicNote, contentDescription = null)
  }
}
