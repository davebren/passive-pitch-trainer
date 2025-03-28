package org.eski.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.tween
import kotlinx.coroutines.flow.StateFlow
import org.eski.game.GameMetaState

@Composable
fun PauseOverlay(
    state: StateFlow<GameMetaState>,
    modifier: Modifier = Modifier
) {
    val state by state.collectAsState()

    // Only display the overlay when the game is paused
    AnimatedVisibility(
        visible = state == GameMetaState.Paused,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.6f)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "PAUSED",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}