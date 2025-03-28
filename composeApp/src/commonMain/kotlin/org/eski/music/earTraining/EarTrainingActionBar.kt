package org.eski.music.earTraining

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.eski.ui.AppColors
import org.eski.ui.util.PlatformBackHandler
import org.jetbrains.compose.resources.stringResource
import passivepitch.composeapp.generated.resources.Res
import passivepitch.composeapp.generated.resources.backButton


private val backButtonEnterAnimation = fadeIn() + scaleIn()
private val backButtonExitAnimation = fadeOut(tween(durationMillis = 1000, easing = LinearEasing)) + scaleOut()

@Composable
fun EarTrainingActionBar(vm: EarTrainingViewModel) {
  Row {
    BackButton(vm)
    Spacer(modifier = Modifier.fillMaxWidth().weight(1f))
  }
}

@Composable
private fun BackButton(vm: EarTrainingViewModel) {
  val visible by vm.backButtonVisible.collectAsState()
  if (visible) PlatformBackHandler(onBack = { vm.backClicked() })

  AnimatedVisibility(
    visible = visible,
    enter = backButtonEnterAnimation,
    exit = backButtonExitAnimation
  ) {
    IconButton(onClick = { vm.backClicked() }) {
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = stringResource(Res.string.backButton),
        tint = AppColors.sightReadBack()
      )
    }
  }
}