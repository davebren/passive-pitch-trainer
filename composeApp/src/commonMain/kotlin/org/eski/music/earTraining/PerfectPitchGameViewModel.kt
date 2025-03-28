package org.eski.music.earTraining

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.eski.audio.SoundEffect
import org.eski.audio.SoundPlayer
import org.eski.music.earTraining.model.NoteFeedback
import org.eski.music.earTraining.model.PerfectPitchGameState
import org.eski.music.earTraining.model.PerfectPitchLevel
import org.eski.music.ui.noteInputs.NoteInputs
import org.eski.pitch.ui.game.model.FeedbackState

class PerfectPitchGameViewModel(val scope: CoroutineScope, val soundPlayer: SoundPlayer) {
  val gameState = MutableStateFlow<PerfectPitchGameState?>(null)

  var currentPromptJob: Job? = null

  val feedback: StateFlow<FeedbackState> = gameState.map { it?.feedback ?: FeedbackState.none }
    .stateIn(scope, SharingStarted.WhileSubscribed(), FeedbackState.none)
  val feedbackNote: StateFlow<NoteFeedback?> = gameState.map { state ->
    if (state?.feedback == FeedbackState.none) return@map null

    val feedback = state?.previousNote()?.let {
      NoteFeedback(it.first, it.second, state.feedback)
    }
    feedback
  }.stateIn(scope, SharingStarted.WhileSubscribed(), null)

  init {
    scope.launch {
      NoteInputs.noteClickedFlow.collectLatest { clickedNote ->
        gameState.value?.currentPromptNote?.first?.let { promptNote ->
          if (clickedNote.matchesChromatic(promptNote)) correctNote()
          else incorrectNote()
        }
      }
    }
    scope.launch {
      feedback.collectLatest {
        when(it) {
          FeedbackState.correct -> {}
          FeedbackState.incorrect -> SoundPlayer.playSoundEffect(SoundEffect.shortBuzz)
          FeedbackState.none -> {}
        }
      }
    }
  }

  fun startGame(level: PerfectPitchLevel) {
    currentPromptJob?.cancel()
    initializeGameState(level)
    nextPrompt(null)
  }

  private fun initializeGameState(level: PerfectPitchLevel) {
    gameState.value = PerfectPitchGameState(
      level = level,
      nextNotes = level.generateNotes(count = 100)
    )
  }

  private fun nextPrompt(correct: Boolean?) {
    println("nextPrompt: ${gameState.value}")
    currentPromptJob?.cancel()
    currentPromptJob = scope.launch {
      if (correct == false) delay(3000)
      else delay(2000)

      gameState.value = gameState.value?.endIfTimeElapsed()?.let { state ->
        if(!state.ended) {
          gameState.value?.pullNextNote() ?: throw IllegalStateException()
        } else state
      }

      if (gameState.value?.ended != false) return@launch

      gameState.value?.currentPromptNote?.first?.let {
        soundPlayer.playNote(it)
      } ?: throw IllegalStateException()

      delay(gameState.value?.level?.promptMaxDuration ?: return@launch)
      incorrectNote() // Response time elapsed.
    }
  }

  fun correctNote() {
    currentPromptJob?.cancel()
    gameState.value = gameState.value?.correctUpdate()
    nextPrompt(true)
  }

  fun incorrectNote() {
    currentPromptJob?.cancel()
    gameState.value = gameState.value?.incorrectUpdate()
    nextPrompt(false)
  }

  fun quit() {
    currentPromptJob?.cancel()
    gameState.value = gameState.value?.end()
  }
}
