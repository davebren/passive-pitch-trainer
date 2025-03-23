package org.eski.music.model

class Scale(val name: String, val keySignature: KeySignature) {
  companion object {
    val cMajor = Scale("C Major", KeySignature.cMajor)
    val gMajor = Scale("G Major", KeySignature.gMajor)
    val dMajor = Scale("D Major", KeySignature.dMajor)
    val aMajor = Scale("A Major", KeySignature.aMajor)
    val eMajor = Scale("E Major", KeySignature.eMajor)
    val bMajor = Scale("B Major", KeySignature.bMajor)
    val fSharpMajor = Scale("F♯ Major", KeySignature.fSharpMajor)
    val cSharpMajor = Scale("C♯ Major", KeySignature.cSharpMajor)

    val fMajor = Scale("F Major", KeySignature.fMajor)
    val bFlatMajor = Scale("B♭ Major", KeySignature.bFlatMajor)
    val eFlatMajor = Scale("E♭ Major", KeySignature.eFlatMajor)
    val aFlatMajor = Scale("A♭ Major", KeySignature.aFlatMajor)
    val dFlatMajor = Scale("D♭ Major", KeySignature.dFlatMajor)
    val gFlatMajor = Scale("G♭ Major", KeySignature.gFlatMajor)
    val cFlatMajor = Scale("C♭ Major", KeySignature.cFlatMajor)

    val aMinor = Scale("A Minor", KeySignature.cMajor)
    val eMinor = Scale("E Minor", KeySignature.gMajor)
    val bMinor = Scale("B Minor", KeySignature.dMajor)
    val fSharpMinor = Scale("F♯ Minor", KeySignature.aMajor)
    val cSharpMinor = Scale("C♯ Minor", KeySignature.eMajor)
    val gSharpMinor = Scale("G♯ Minor", KeySignature.bMajor)
    val dSharpMinor = Scale("D♯ Minor", KeySignature.fSharpMajor)
    val aSharpMinor = Scale("A♯ Minor", KeySignature.cSharpMajor)

    val dMinor = Scale("D Minor", KeySignature.fMajor)
    val gMinor = Scale("G Minor", KeySignature.bFlatMajor)
    val cMinor = Scale("C Minor", KeySignature.eFlatMajor)
    val fMinor = Scale("F Minor", KeySignature.aFlatMajor)
    val bFlatMinor = Scale("B♭ Minor", KeySignature.dFlatMajor)
    val eFlatMinor = Scale("E♭ Minor", KeySignature.gFlatMajor)
    val aFlatMinor = Scale("A♭ Minor", KeySignature.cFlatMajor)


    val all: List<Pair<Scale, Scale>> = listOf(
      Pair(cMajor, aMinor),
      Pair(gMajor, eMinor),
      Pair(dMajor, bMinor),
      Pair(aMajor, fSharpMinor),
      Pair(eMajor, cSharpMinor),
      Pair(bMajor, gSharpMinor),
      Pair(fSharpMajor, dSharpMinor),
      Pair(cSharpMajor, aSharpMinor),
      Pair(fMajor, dMinor),
      Pair(bFlatMajor, gMinor),
      Pair(eFlatMajor, cMinor),
      Pair(aFlatMajor, fMinor),
      Pair(dFlatMajor, bFlatMinor),
      Pair(gFlatMajor, eFlatMinor),
      Pair(cFlatMajor, aFlatMinor)
    )

    val major = all.map { it.first }
    val minor = all.map { it.second }
  }
}