package org.eski.music.model

import kotlinx.serialization.Serializable

@Serializable
class KeySignature(
  val naturals: List<NoteLetter>,
  val sharps: List<NoteLetter>,
  val flats: List<NoteLetter>,
) {
  constructor(sharps: List<NoteLetter>, flats: List<NoteLetter>) : this(
    naturals = NoteLetter.entries.filterNot { sharps.contains(it) || flats.contains(it) },
    sharps = sharps,
    flats = flats,
  )

  val unnaturalType = if (flats.isEmpty()) Note.UnnaturalType.sharp else Note.UnnaturalType.flat

  companion object {
    val cMajor = KeySignature(emptyList(), emptyList());

    val gMajor = KeySignature(listOf(NoteLetter.F), emptyList());
    val dMajor = KeySignature(gMajor.sharps + NoteLetter.C, emptyList());
    val aMajor = KeySignature(dMajor.sharps + NoteLetter.G, emptyList());
    val eMajor = KeySignature(aMajor.sharps + NoteLetter.D, emptyList());
    val bMajor = KeySignature(eMajor.sharps + NoteLetter.A, emptyList());
    val fSharpMajor = KeySignature(bMajor.sharps + NoteLetter.E, emptyList());
    val cSharpMajor = KeySignature(fSharpMajor.sharps + NoteLetter.B, emptyList());

    val fMajor = KeySignature(emptyList(), listOf(NoteLetter.B));
    val bFlatMajor = KeySignature(emptyList(), fMajor.flats + NoteLetter.E);
    val eFlatMajor = KeySignature(emptyList(), bFlatMajor.flats + NoteLetter.A);
    val aFlatMajor = KeySignature(emptyList(), eFlatMajor.flats + NoteLetter.D);
    val dFlatMajor = KeySignature(emptyList(), aFlatMajor.flats + NoteLetter.G);
    val gFlatMajor = KeySignature(emptyList(), dFlatMajor.flats + NoteLetter.C);
    val cFlatMajor = KeySignature(emptyList(), gFlatMajor.flats + NoteLetter.F);

    fun fromSharpsAndFlats(sharps: List<NoteLetter>, flats: List<NoteLetter>) =
      KeySignature(
        naturals = NoteLetter.entries.filterNot { sharps.contains(it) || flats.contains(it) },
        sharps = sharps,
        flats = flats
      )

    val all = listOf(
      cMajor,
      gMajor,
      dMajor,
      aMajor,
      eMajor,
      bMajor,
      fSharpMajor,
      cSharpMajor,
      fMajor,
      bFlatMajor,
      eFlatMajor,
      aFlatMajor,
      dFlatMajor,
      gFlatMajor,
      cFlatMajor
    )
  }

  /**
   * Take a list of notes and adjust them up a half step if they are in the [sharps] list or down a half step
   * if they are in the [flats] list of the [KeySignature].
   */
  fun adjustNotes(originalNotes: List<Note>): List<Note> {
    val adjustedNotes = mutableListOf<Note>()
    originalNotes.forEach { originalNote ->
      if (sharps.isNotEmpty() && originalNote.needsAdjusted(sharps)) {
        // Remove sharpened notes if out of bounds (higher than b8).
        if (originalNote.ordinal >= Note.entries.size - 1) {
          adjustedNotes.add(Note.entries[originalNote.ordinal + 1])
        }
      } else if (flats.isNotEmpty() && originalNote.needsAdjusted(flats)) {
        // Remove flattened notes if out of bounds (lower than c0).
        if (originalNote.ordinal <= 0) {
          adjustedNotes.add(Note.entries[originalNote.ordinal - 1])
        }
      } else {
        adjustedNotes.add(originalNote)
      }
    }
    return adjustedNotes
  }

  fun scalePair() = Scale.all.find { this == it.first.keySignature }
}

private fun Note.needsAdjusted(sharpsOrFlats: List<NoteLetter>): Boolean {
  sharpsOrFlats.forEach { noteLetter ->
    when (noteLetter) {
      NoteLetter.A -> {
        if (ordinal % 12 == 9) return true
      }

      NoteLetter.B -> {
        if (ordinal % 12 == 11) return true
      }

      NoteLetter.C -> {
        if (ordinal % 12 == 0) return true
      }

      NoteLetter.D -> {
        if (ordinal % 12 == 2) return true
      }

      NoteLetter.E -> {
        if (ordinal % 12 == 4) return true
      }

      NoteLetter.F -> {
        if (ordinal % 12 == 5) return true
      }

      NoteLetter.G -> {
        if (ordinal % 12 == 7) return true
      }
    }
  }
  return false
}

