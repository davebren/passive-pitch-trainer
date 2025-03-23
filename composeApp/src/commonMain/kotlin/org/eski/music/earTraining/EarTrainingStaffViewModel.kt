package org.eski.music.earTraining

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.eski.music.model.Clef
import org.eski.music.model.KeySignature
import org.eski.music.model.Note
import org.eski.music.model.StaffConfig
import org.eski.music.staff.StaffColors
import org.eski.music.staff.StaffNote
import org.eski.music.staff.StaffViewModel

class EarTrainingStaffViewModel(val host: EarTrainingHost): StaffViewModel {
  override val displayNotes = MutableStateFlow<List<StaffNote>>(emptyList())
  override val stackClefs = MutableStateFlow<Boolean>(true)

  override val correctEmitter = MutableSharedFlow<Note>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  override val incorrectEmitter = MutableSharedFlow<Note>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
  override val hostSize = host.size
  override val showQueryBackground = MutableStateFlow(false)
  override val showGameInfo = MutableStateFlow(false) // TODO:
  override val colors = MutableStateFlow<StaffColors>(StaffColors())

  val config = StaffConfig(Clef.Combo.trebleAndBass, KeySignature.cMajor)
}