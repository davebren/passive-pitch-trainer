package org.eski.pitch.ui.game.data

import com.russhwolf.settings.Settings
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import org.eski.pitch.ui.game.model.FeedbackMode
import org.eski.util.safeJsonDecode
import org.eski.util.safeJsonEncode


class GameSettings(val settings: Settings) {
  val scope = MainScope()

  companion object {
    private const val settingsKey = "settings.game"
    private const val feedbackModeKey = "$settingsKey.feedbackModes"

  }

  val feedbackMode = MutableStateFlow<List<FeedbackMode>>(listOf(FeedbackMode.icon))

  init {
    val feedbackModeJson = settings.getStringOrNull(feedbackModeKey)
    feedbackModeJson?.let { jsonString ->
      val decodedSetting = jsonString.safeJsonDecode<List<FeedbackMode>>()
      decodedSetting?.let { feedbackMode.value = it }
    }
  }

  fun toggleFeedbackMode(mode: FeedbackMode) {
    val oldSetting = feedbackMode.value
    val enabled = oldSetting.find { it == mode } != null

    if (enabled) {
      feedbackMode.value = oldSetting.filterNot { it == mode }
    } else {
      feedbackMode.value = oldSetting.toMutableList().apply { add(mode) }
    }
    saveFeedbackSetting()
  }

  private fun saveFeedbackSetting() {
    feedbackMode.value.safeJsonEncode()?.let { settings.putString(feedbackModeKey, it) }
  }
}