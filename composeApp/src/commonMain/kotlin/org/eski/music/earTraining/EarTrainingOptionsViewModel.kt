package org.eski.music.earTraining

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.model.PerfectPitchLevel
import org.eski.pitch.ui.game.model.GameMetaState
import org.eski.ui.views.selectors.DropdownSelectorViewModel

class EarTrainingOptionsViewModel(
  val scope: CoroutineScope,
  gameMetaState: StateFlow<GameMetaState>,
) {
  val levelSelectorVisible = gameMetaState.map { state ->
    state == GameMetaState.NotStarted
  }.stateIn(scope, SharingStarted.WhileSubscribed(), true)
  val levelSelected = MutableStateFlow<PerfectPitchLevel>(PerfectPitchLevel.levels.first()) // TODO:
  val levelSelectedName = levelSelected.map { it.name }.stateIn(scope, SharingStarted.WhileSubscribed(), "")
  val levelSelectorOptions = MutableStateFlow<List<DropdownSelectorViewModel.Option>>(
    PerfectPitchLevel.levels.map { PerfectPitchLevelOption(it, true) }
  ) // TODO:
  val levelSelectorDropdown = DropdownSelectorViewModel(
    selectedName = levelSelectedName,
    options = levelSelectorOptions,
    onSelectedOption = { (it as? PerfectPitchLevelOption)?.let { levelOption -> levelSelected.value = levelOption.level } }
  )

  private class PerfectPitchLevelOption(
    val level: PerfectPitchLevel,
    enabled: Boolean,
  ): DropdownSelectorViewModel.Option(level.name, enabled)
}