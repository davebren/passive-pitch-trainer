package org.eski.ui

import androidx.compose.ui.graphics.Color

object AppColors {
  var theme = ColorTheme.light
  
  fun homeBackground() = Palette.one.themeColors[theme.ordinal]
  fun homeMuteButton() = Palette.four.themeColors[theme.ordinal]

  fun profileBackground() = Palette.seven.themeColors[theme.ordinal]
  fun profileButtonIcon() = Palette.five.themeColors[theme.ordinal]
  fun profileSyncBackground() = Palette.five.themeColors[theme.ordinal]
  fun profileSyncIcon() = Palette.seven.themeColors[theme.ordinal]
  fun profileSyncText() = profileSyncIcon()
  fun signInText() = Palette.five.themeColors[theme.ordinal]
  fun googleAuthButtonBackground() = Palette.five.themeColors[theme.ordinal]
  fun googleAuthButtonText() = Palette.seven.themeColors[theme.ordinal]
  fun googleAuthLoadingIcon() = googleAuthButtonText()
  fun profileBack() = Palette.five.themeColors[theme.ordinal]

  fun button() = Palette.five.themeColors[theme.ordinal]
  fun accent() = Palette.four.themeColors[theme.ordinal]
  fun cardBackground() = Palette.five.themeColors[theme.ordinal]
  fun sightReadBackground() = accent()
  fun sightReadBack() = Palette.one.themeColors[theme.ordinal]
  fun sightReadMute() = Palette.one.themeColors[theme.ordinal]
  fun sightReadMenuSelectorText() = Palette.seven.themeColors[theme.ordinal]
  fun sightReadMenuSelectorDisabledText() = sightReadMenuSelectorText().copy(alpha = .4f)

  fun earTrainingBackground() = Palette.two.themeColors[theme.ordinal]

  fun buttonText() = Palette.one.themeColors[theme.ordinal]
  fun buttonIcon() = Palette.seven.themeColors[theme.ordinal]
  fun buttonPressed() = Palette.eight.themeColors[theme.ordinal]
  fun playButtonBackground() = Palette.twelve.themeColors[theme.ordinal]
  fun playButtonBorder() = Palette.seven.themeColors[theme.ordinal]
  fun playButtonIcon() = Palette.five.themeColors[theme.ordinal]
  fun pregameInstructions() = Palette.five.themeColors[theme.ordinal]
  fun countdownText() = Palette.five.themeColors[theme.ordinal]
  fun pauseText() = Palette.five.themeColors[theme.ordinal]
  fun quitButtonBackground() = Palette.thirteen.themeColors[theme.ordinal]
  fun quitButtonBorder() = Palette.seven.themeColors[theme.ordinal]
  fun quitButtonIcon() = Palette.five.themeColors[theme.ordinal]


  fun staffBackground() = Palette.five.themeColors[theme.ordinal]
  fun staff() = Palette.seven.themeColors[theme.ordinal]
  fun menuCardBackground() = Palette.five.themeColors[theme.ordinal]
  fun listDivider() = Palette.eight.themeColors[theme.ordinal]
  fun radioButtonUnselected() = Palette.nine.themeColors[theme.ordinal]
  fun radioButtonSelected() = Palette.four.themeColors[theme.ordinal]

  fun notesWheelBackground() = Palette.ten.themeColors[theme.ordinal]
  fun notesWheelNaturalNoteBackground() = Palette.five.themeColors[theme.ordinal]
  fun notesWheelNaturalNotePressedBackground() = Palette.eight.themeColors[theme.ordinal]
  fun notesWheelSharpNoteBackground() = Palette.eleven.themeColors[theme.ordinal]
  fun notesWheelSharpNotePressedBackground() = Palette.seven.themeColors[theme.ordinal]
  fun naturalNotesTextStyle() = Palette.seven.themeColors[theme.ordinal]
  fun sharpNotesTextStyle() = Palette.five.themeColors[theme.ordinal]

  fun note() = Palette.seven.themeColors[theme.ordinal]
  fun noteQueryBackground() = Palette.fourteen.themeColors[theme.ordinal]

  fun gameInfoBackground() = Palette.five.themeColors[theme.ordinal]
  fun correctCountIcon() = Palette.twelve.themeColors[theme.ordinal]
  fun correctCount() = Palette.seven.themeColors[theme.ordinal]
  fun correctFeedbackColor() = Color.Green.copy(alpha = 0f)
  fun incorrectCountIcon() = Palette.thirteen.themeColors[theme.ordinal]
  fun incorrectCount() = Palette.seven.themeColors[theme.ordinal]
  fun incorrectFeedbackColor() = Color.Red.copy(alpha =0f)
  fun score() = Palette.seven.themeColors[theme.ordinal]
  fun timer() = Palette.seven.themeColors[theme.ordinal]
  fun timerWarning() = Palette.fifteen.themeColors[theme.ordinal]

  fun postGameBackground() = Palette.five.themeColors[theme.ordinal]
  fun postGameText() = Palette.seven.themeColors[theme.ordinal]

  fun pianoWhiteKeyBackground() = Palette.five.themeColors[theme.ordinal]
  fun pianoWhiteKeyBorder() = Palette.nine.themeColors[theme.ordinal]
  fun pianoWhiteKeyText() = Palette.seven.themeColors[theme.ordinal]
  fun pianoBlackKeyBackground() = Palette.eleven.themeColors[theme.ordinal]
  fun pianoBlackKeyBorder() = Palette.seven.themeColors[theme.ordinal]
  fun pianoBlackKeyText() = Palette.five.themeColors[theme.ordinal]
}

enum class ColorTheme {
  light, dark
}

enum class Palette(val themeColors: List<Color>) {
  one(listOf(
    Color(45, 48, 121),
    Color(0xff, 0xff, 0xff)
  )),
  two(listOf(
    Color(102, 45, 145),
    Color(102, 45, 145),
  )),
  three(listOf(
    Color(147, 149, 152),
    Color(147, 149, 152),
  )),
  four(listOf(
    Color(9, 188, 212), // accent blue
    Color(0x44, 0x44, 0x44),
  )),
  five(listOf(
    Color(255, 255, 255), // white
    Color(0, 0, 0),
  )),
  six(listOf(
    Color(147, 149, 152),
    Color(147, 149, 152),
  )),
  seven(listOf(
    Color(0x44, 0x44, 0x44), // dark gray
    Color(0xe0, 0xe0, 0xe0),
  )),
  eight(listOf(
    Color(0xe0, 0xe0, 0xe0), // light gray
    Color(0x44, 0x44, 0x44),
  )),
  nine(listOf(
    Color(0x82, 0x82, 0x82), // medium gray
    Color(0x82, 0x82, 0x82),
  )),
  ten(listOf(
    Color(87, 91, 193), // lighter purple
    Color(87, 91, 193),
  )),
  eleven(listOf(
    Color(0x33, 0x33, 0x33), // darkest gray
    Color(0x33, 0x33, 0x33),
  )),
  twelve(listOf(
    Color(0x0b, 0x93, 0x0b),
    Color(0x0b, 0x93, 0x0b),
  )),
  thirteen(listOf(
    Color(0xc9, 0x6f, 0x65), // red
    Color(0xc9, 0x6f, 0x65),
  )),
  fourteen(listOf(
    Color(0x80, 0xff, 0x00),
    Color(0x80, 0xff, 0x00)
  )),
  fifteen(listOf(
    Color(0xA8, 0x01, 0X02),
    Color(0xA8, 0x01, 0X02),
  )),
  ;
}