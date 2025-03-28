package org.eski.ui.views.valueForValue

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow

class ValueForValueViewModel(
  scope: CoroutineScope,
) {
  val buttonVisible = MutableStateFlow<Boolean>(true)
  val menuShowing = MutableStateFlow<Boolean>(false)

  fun clicked() { menuShowing.value = true }
  fun dismissed() { menuShowing.value = false }
}