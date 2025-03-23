package org.eski.ui.views.startButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.util.topWindowInset

@Composable
public fun StartButtonInstructions(
  header: String,
  subtext: String,
  color: Color
) {

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(grid2)
    )
    {
      topWindowInset()

      Text(
        text = header,
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        color = color,
        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
      )
      Spacer(modifier = Modifier.height(grid))
      Text(
        text = subtext,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        color = color,
        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
      )
    }
  }
}