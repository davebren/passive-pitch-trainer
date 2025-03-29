package org.eski.music.earTraining.options

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import kotlinx.atomicfu.TraceBase.None.append
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.model.PerfectPitchLevel
import org.eski.game.GameMetaState
import org.eski.pitch.ui.game.data.EarTrainingStatsData
import org.eski.ui.views.selectors.DropdownSelectorViewModel

class EarTrainingOptionsViewModel(
  val scope: CoroutineScope,
  gameMetaState: StateFlow<GameMetaState>,
  earTrainingStatsData: EarTrainingStatsData,
) {
  val optionColors = MutableStateFlow<EarTrainingOptionColors>(EarTrainingOptionColors())

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
  val levelSubtext = levelSelected.map { it.subtext() }
    .stateIn(scope, SharingStarted.WhileSubscribed(), "")
  val levelSubtextAnnotated: StateFlow<AnnotatedString> = combine(levelSubtext, optionColors) { text, colors ->
    val textParts = text.split('|')

    buildAnnotatedString {
      textParts.forEachIndexed { index, textPart ->
        append(textPart)
        if (index == textParts.size - 1) return@forEachIndexed

        withStyle(style = SpanStyle(colors.textDivider)) {
          append('|')
        }
      }
    }
  }.stateIn(scope, SharingStarted.WhileSubscribed(), AnnotatedString(""))

  val highScoreVisible: StateFlow<Boolean> = gameMetaState.map {
    when(it) {
      GameMetaState.NotStarted -> true
      GameMetaState.Running, GameMetaState.Paused, GameMetaState.GameOver -> false
    }
  }.stateIn(scope, SharingStarted.WhileSubscribed(), false)

  val highScoreText: StateFlow<String> = levelSelected.map {
    earTrainingStatsData.perfectPitchHighScore(it.index).toString()
  }.stateIn(scope, SharingStarted.WhileSubscribed(), "0")

  private class PerfectPitchLevelOption(
    val level: PerfectPitchLevel,
    enabled: Boolean,
  ): DropdownSelectorViewModel.Option(level.name, enabled)
}