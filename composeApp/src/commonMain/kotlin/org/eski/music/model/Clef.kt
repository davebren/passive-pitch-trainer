package org.eski.music.model

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import org.eski.music.model.Clef.Combo.trebleAndBass
import org.jetbrains.compose.resources.stringResource
import passivepitch.composeapp.generated.resources.Res
import passivepitch.composeapp.generated.resources.bassOnly
import passivepitch.composeapp.generated.resources.trebleOnly
import passivepitch.composeapp.generated.resources.treblePlusBass

@Serializable
enum class Clef {
  treble, bass;

  enum class Combo(val clefs: List<Clef>, val stableIndex: Int, val size: Int) {
    treble(listOf(Clef.treble), 0, 1),
    bass(listOf(Clef.bass), 1, 1),
    trebleAndBass(listOf(Clef.treble, Clef.bass), 2, 2)
  }

  companion object {
    fun allCombos() = Combo.entries.toList()

    fun Set<Clef>.combo() =
      if (size == 1 && contains(treble)) Combo.treble
      else if (size == 1 && contains(bass)) Combo.bass
      else trebleAndBass

    fun List<Clef>.combo() =
      if (size == 1 && contains(treble)) Combo.treble
      else if (size == 1 && contains(bass)) Combo.bass
      else trebleAndBass
  }
}

@Composable
fun Set<Clef>.optionString(): String = if (size == 1 && contains(Clef.treble)) stringResource(Res.string.trebleOnly)
  else if (size == 1 && contains(Clef.bass)) stringResource(Res.string.bassOnly)
  else stringResource(Res.string.treblePlusBass)

@Composable
fun Clef.Combo.optionString(): String = when(this) {
  Clef.Combo.treble -> stringResource(Res.string.trebleOnly)
  Clef.Combo.bass -> stringResource(Res.string.bassOnly)
  trebleAndBass -> stringResource(Res.string.treblePlusBass)
}