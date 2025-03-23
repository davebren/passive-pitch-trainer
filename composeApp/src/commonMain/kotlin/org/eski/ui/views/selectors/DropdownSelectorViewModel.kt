package org.eski.ui.views.selectors

import kotlinx.coroutines.flow.StateFlow

class DropdownSelectorViewModel(
  val selectedName: StateFlow<String>,
  val options: StateFlow<List<Option>>,
  val onSelected: (Int) -> Unit = {},
  val onSelectedOption: (Option) -> Unit = {},
) {
  open class Option(
    val name: String,
    val enabled: Boolean
  )
}