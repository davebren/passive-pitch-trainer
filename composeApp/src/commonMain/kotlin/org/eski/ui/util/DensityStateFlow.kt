
package org.eski.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.flow.MutableStateFlow

val screenDensity = MutableStateFlow<Density>(Density(1f))

@Composable fun DensityObserver() {
  with(LocalDensity.current) {
    screenDensity.value = this
  }
}