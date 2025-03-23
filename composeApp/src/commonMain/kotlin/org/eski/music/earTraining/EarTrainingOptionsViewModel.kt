package org.eski.music.earTraining

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.model.PerfectPitchLevel
import org.eski.ui.views.selectors.DropdownSelectorViewModel

class EarTrainingOptionsViewModel(
  val scope: CoroutineScope
) {
  val levelSelectorVisible = MutableStateFlow(true) // TODO:
  val levelSelected = MutableStateFlow<PerfectPitchLevel>(PerfectPitchLevel.levels.first()) // TODO:
  val levelSelectedName = levelSelected.map { it.name }.stateIn(scope, SharingStarted.WhileSubscribed(), "")
  val levelSelectorOptions = MutableStateFlow<List<DropdownSelectorViewModel.Option>>(
    PerfectPitchLevel.levels.map { DropdownSelectorViewModel.Option(it.name, true) }
  ) // TODO:
  val levelSelectorDropdown = DropdownSelectorViewModel(
    selectedName = levelSelectedName,
    options = levelSelectorOptions,
    onSelected = {}
  )
}