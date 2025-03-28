package org.eski.passivepitch.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eski.music.earTraining.EarTrainingScreen
import org.eski.music.model.KeySignature
import org.eski.pitch.ui.game.InfoDialog
import org.eski.pitch.ui.keybinding.KeyBindingSettings
import org.eski.pitch.ui.keybinding.KeyBindingSettingsDialog
import org.eski.game.GameSettings
import org.eski.intro.IntroSettings
import org.eski.pitch.ui.game.data.EarTrainingStatsData
import org.eski.pitch.ui.keybinding.KeyboardInput
import org.eski.ui.util.grid2
import org.eski.ui.views.spacer

@Composable
fun HomeScreen(
    keyBindings: KeyBindingSettings = org.eski.passivepitch.keyBindingSettings,
    gameSettings: GameSettings = org.eski.passivepitch.gameSettings,
    earTrainingStats: EarTrainingStatsData = org.eski.passivepitch.earTrainingStatsData,
    introSettings: IntroSettings = org.eski.passivepitch.introSettings,
    vm: HomeScreenViewModel = viewModel {
        HomeScreenViewModel(introSettings)
    }
) {

    val introShowing by vm.options.introShowing.collectAsState()
    val settingsShowing by vm.options.settingsShowing.collectAsState()
    val achievementsShowing by vm.options.achievementsShowing.collectAsState()
    val valueForValueShowing by vm.valueForValue.menuShowing.collectAsState()

    val valueForValueButtonVisible by vm.valueForValue.buttonVisible.collectAsState()
    val size by vm.size.collectAsState()
    val actionbarHeight = remember { mutableStateOf(0.dp) }

    KeyboardInput(keyBindings)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .onSizeChanged { vm.onSizeChanged(it) }
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionBarMenu(vm.options, actionbarHeight)
            spacer(grid2)

            Box(modifier = Modifier.fillMaxSize()) {
                Column {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        spacer(grid2)
                    }

                    Spacer(modifier = Modifier.fillMaxHeight().weight(1f))

                    HomeScreenNoteInput(KeySignature.cMajor, zIndex = 2f)
                }
            }
        }
        EarTrainingScreen(
            host = vm,
            zIndex = 1f,
            topBarMargin = actionbarHeight.value,
            gameSettings = gameSettings,
            statsData = earTrainingStats,
        )

//        ValueForValueButton(
//            vm.valueForValue,
//            visible = valueForValueButtonVisible,
//            containerSize = size,
//            onExpanded = { vm.valueForValue.clicked() }
//        )
    }

    if (settingsShowing) {
        KeyBindingSettingsDialog(
            keyBindingSettings = keyBindings,
            gameSettings = gameSettings,
            onDismiss = { vm.options.settingsDismissed() }
        )
    }

    if (introShowing) {
        InfoDialog(
            onDismiss = { vm.options.introDismissed() },
            onOpenSettings = { vm.options.settingsClicked() }
        )
    }

//    ValueForValueScreen(vm.valueForValue, valueForValueShowing)
}