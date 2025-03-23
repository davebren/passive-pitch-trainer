package org.eski.music.earTraining

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.eski.music.model.Clef
import org.eski.music.model.KeySignature
import org.eski.music.model.StaffConfig
import org.eski.music.staff.Staff
import org.eski.music.staff.StaffConst.staffHeightDoubleClef
import org.eski.music.staff.StaffConst.staffHeightSingleClef
import org.eski.music.staff.StaffViewModel
import org.eski.ui.util.grid2

private val staffEnterAnimation = slideInHorizontally(animationSpec = tween(500, 200), initialOffsetX = { width -> width })
private val staffExitAnimation = slideOutHorizontally(animationSpec = tween(500), targetOffsetX = { width -> width })

@Composable
fun EarTrainingStaff(modifier: Modifier, vm: EarTrainingStaffViewModel, open: Boolean) {
  val hostSize by vm.host.size.collectAsState()

  Box(
    modifier = modifier.height(staffHeightDoubleClef.output(hostSize.height))
  ) {
    AnimatedStaff(
      vm = vm,
      staffConfig = StaffConfig(Clef.Combo.trebleAndBass, KeySignature.cMajor),
      visible = open,
    )
  }
}

@Composable
private fun AnimatedStaff(
  vm: StaffViewModel,
  staffConfig: StaffConfig,
  visible: Boolean,
) {
  val hostSize by vm.hostSize.collectAsState()
  val showGameInfo by vm.showGameInfo.collectAsState()

  val height = if (staffConfig.clefs.size == 1) {
    staffHeightSingleClef.output(hostSize.height)
  } else {
    staffHeightDoubleClef.output(hostSize.height)
  }

  if (!showGameInfo) {
    Row {
      if (visible) {
//        SightReadGameInfo(gameViewModel, true) TODO:
      }

      AnimatedVisibility(
        visible = visible,
        enter = staffEnterAnimation,
        exit = staffExitAnimation
      ) {
        Staff(
          vm,
          config = staffConfig,
          height = height,
          paddingStart = grid2,
        )
      }
    }
  } else {
    AnimatedVisibility(
      visible = visible,
      enter = staffEnterAnimation,
      exit = staffExitAnimation
    ) {
      Staff(
        vm,
        config = staffConfig,
        height = height,
        paddingStart = grid2,
      ) {
        if (visible) {
//          SightReadGameInfo(gameViewModel, false) TODO:
        }
      }
    }
  }
}