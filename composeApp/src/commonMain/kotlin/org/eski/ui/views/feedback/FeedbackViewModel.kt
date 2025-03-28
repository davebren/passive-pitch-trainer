package org.eski.ui.views.feedback

import kotlinx.coroutines.flow.StateFlow
import org.eski.game.FeedbackMode
import org.eski.game.FeedbackState
import org.eski.game.GameSettings

class FeedbackViewModel(val gameSettings: GameSettings, val state: StateFlow<FeedbackState>) {
  val modesEnabled: StateFlow<List<FeedbackMode>> = gameSettings.feedbackMode
}