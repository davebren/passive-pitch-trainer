package org.eski.music.earTraining

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import org.eski.ui.util.grid
import org.eski.ui.util.grid2
import org.eski.ui.views.topWindowInsetSpacer

@Composable
fun EarTrainingScreen(
  host: EarTrainingHost,
  vm: EarTrainingViewModel = viewModel { EarTrainingViewModel(host) },
  zIndex: Float,
) {
  val open by host.earTrainingOpen.collectAsState()

  Box(modifier = Modifier.fillMaxSize()
    .zIndex(zIndex)
  ) {
    val earTrainingLevelSelectorExpanded = remember { mutableStateOf(false) }

    Column(
      modifier = Modifier.fillMaxSize()
    ) {
      topWindowInsetSpacer()
      EarTrainingActionBar(vm)
      Spacer(Modifier.height(grid))

      Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
          EarTrainingStaff(vm.staff, open)
          Spacer(modifier = Modifier.height(grid2))

//          Selectors(
//            vm = vm,
//            sightReadLevelSelectorExpanded = earTrainingLevelSelectorExpanded,
//            sightReadKeySelectorExpanded = sightReadKeySelectorExpanded,
//            clefsSelectorExpanded = clefsSelectorExpanded,
//            includeLevelSelector = !overlaySelectors,
//            includeKeySelector = !overlaySelectors,
//            includeClefsSelector = useRadioClefsSelector,
//            useRadioClefsSelector = useRadioClefsSelector,
//          )
        }

//        if (overlaySelectors) {
//          Selectors(
//            vm = vm,
//            sightReadLevelSelectorExpanded = earTrainingLevelSelectorExpanded,
//            sightReadKeySelectorExpanded = sightReadKeySelectorExpanded,
//            clefsSelectorExpanded = clefsSelectorExpanded,
//            includeLevelSelector = overlaySelectors,
//            includeKeySelector = overlaySelectors,
//            includeClefsSelector = !useRadioClefsSelector,
//            useRadioClefsSelector = useRadioClefsSelector,
//          )
//        }
      }
    }
  }
}

