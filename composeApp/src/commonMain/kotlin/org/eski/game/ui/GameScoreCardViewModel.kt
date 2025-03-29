package org.eski.game.ui

import kotlinx.coroutines.flow.StateFlow

interface GameScoreCardViewModel {
  val visible: StateFlow<Boolean>
  val correct: StateFlow<String>
  val incorrect: StateFlow<String>
  val timeLeft: StateFlow<String>
  val score: StateFlow<String>
  val colors: StateFlow<GameScoreCardColors>
}