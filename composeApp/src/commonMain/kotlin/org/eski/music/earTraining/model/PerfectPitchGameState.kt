package org.eski.music.earTraining.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.eski.music.model.Instrument
import org.eski.music.model.Note
import org.eski.game.FeedbackState
import org.eski.game.FeedbackState.*

data class PerfectPitchGameState(
  val level: PerfectPitchLevel,
  val nextNotes: List<Pair<Note, Instrument>>,
  val previousNotes: List<Pair<Note, Instrument>> = emptyList(),
  val currentPromptNote: Pair<Note, Instrument>? = null,
  val correctCount: Int = 0,
  val incorrectCount: Int = 0,
  val feedback: FeedbackState = none,
  val startTime: Instant = Clock.System.now(),
  val ended: Boolean = false,
) {
  fun pullNextNote(): PerfectPitchGameState {
    var nextNote = nextNotes.firstOrNull()
    val newPreviousNotes = currentPromptNote?.let {
      previousNotes.plus(it)
    } ?: previousNotes
    println("pullNextNote: ${newPreviousNotes.size}")

    val newNextNotes = if (nextNote == null) {
      level.generateNotes(newPreviousNotes, count = 10).let {
        nextNote = it.first()
        it.subList(1, it.size)
      }
    } else nextNotes.subList(1, nextNotes.size)

    return copy(
      nextNotes = newNextNotes,
      previousNotes = newPreviousNotes,
      currentPromptNote = nextNote,
      feedback = none,
    )
  }

  fun correctUpdate(): PerfectPitchGameState = copy(
    currentPromptNote = null,
    previousNotes = currentPromptNote?.let { previousNotes.plus(it) } ?: previousNotes,
    correctCount = correctCount +1,
    feedback = correct,
  )
  fun incorrectUpdate(): PerfectPitchGameState = copy(
    currentPromptNote = null,
    previousNotes = currentPromptNote?.let { previousNotes.plus(it) } ?: previousNotes,
    incorrectCount = incorrectCount +1,
    feedback = incorrect,
  )

  fun endIfTimeElapsed(): PerfectPitchGameState {
    return if (level.duration < Clock.System.now().minus(startTime)) {
      end()
    } else this
  }
  fun end(): PerfectPitchGameState = copy(currentPromptNote = null, ended = true, feedback = none)

  fun previousNote(): Pair<Note, Instrument>? = previousNotes.lastOrNull()
}