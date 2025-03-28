package org.eski.music.earTraining

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.eski.music.earTraining.model.PerfectPitchLevel
import org.eski.music.model.Note
import org.eski.music.staff.StaffColors
import org.eski.music.staff.StaffNote
import org.eski.music.staff.StaffViewModel
import org.eski.pitch.ui.game.model.GameMetaState

class EarTrainingStaffViewModel(
  val scope: CoroutineScope,
  val host: EarTrainingHost,
  val gameState: StateFlow<GameMetaState>,
  val levelSelected: StateFlow<PerfectPitchLevel>,
  val perfectPitchGameViewModel: PerfectPitchGameViewModel,
): StaffViewModel {
  override val displayNotes: StateFlow<List<StaffNote>> = combine(gameState, levelSelected, perfectPitchGameViewModel.feedbackNote) {
    gameState, level, perfectPitchFeedback ->

    val notes = when (gameState) {
      GameMetaState.NotStarted, GameMetaState.GameOver -> {
        level.notes.map { StaffNote(it, it.defaultClef) }
      }
      GameMetaState.Running -> {
        perfectPitchFeedback?.note?.let { listOf(StaffNote(it, it.defaultClef)) } ?: emptyList()
      }
      else -> emptyList()
    }
    notes
  }.stateIn(scope, SharingStarted.WhileSubscribed(), emptyList<StaffNote>())

  override val stackClefs = MutableStateFlow<Boolean>(true)

  override val correctEmitter = MutableSharedFlow<Note>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  override val incorrectEmitter = MutableSharedFlow<Note>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  override val hostSize = host.size
  override val showQueryBackground = MutableStateFlow(false)
  override val showGameInfo = MutableStateFlow(false) // TODO:
  override val colors = MutableStateFlow<StaffColors>(StaffColors())
}