package org.eski.music.staff

import androidx.compose.ui.unit.DpSize
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.eski.music.model.Note

interface StaffViewModel {
  val displayNotes: StateFlow<List<StaffNote>>
  val stackClefs: StateFlow<Boolean>
  val correctEmitter: SharedFlow<Note>?
  val incorrectEmitter: SharedFlow<Note>?
  val hostSize: StateFlow<DpSize>
  val showQueryBackground: StateFlow<Boolean>
  val showGameInfo: StateFlow<Boolean>
  val colors: StateFlow<StaffColors>
}

