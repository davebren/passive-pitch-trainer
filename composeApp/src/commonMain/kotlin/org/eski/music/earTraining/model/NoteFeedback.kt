package org.eski.music.earTraining.model

import org.eski.music.model.Instrument
import org.eski.music.model.Note
import org.eski.game.FeedbackState

data class NoteFeedback(
  val note: Note,
  val instrument: Instrument,
  val feedbackState: FeedbackState,
)