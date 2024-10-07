package nl.gn0s1s.osita

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

import Osita._

class OsitaSuite extends ScalaCheckSuite {
  property("osa handles identity correctly") {
    osa("abcde", "abcde") == 0
  }

  property("osa counts deletions correctly") {
    osa("abcde", "cde") == 2
  }

  property("osa counts insertions correctly") {
    osa("cde", "abcde") == 2
  }

  property("osa counts substitutions correctly") {
    osa("abc", "agc") == 1
  }

  property("osa counts transpositions correctly") {
    osa("abc", "acb") == 1
  }

  property("osaWithKeyboard handles identity correctly") {
    osaWithSubstitutionCost("abcde", "abcde")(weightedKeyboardSubstitutionCost) == 0
  }

  property("osaWithKeyboard counts deletions correctly") {
    osaWithSubstitutionCost("abcde", "cde")(weightedKeyboardSubstitutionCost) == 2
  }

  property("osaWithKeyboard counts insertions correctly") {
    osaWithSubstitutionCost("cde", "abcde")(weightedKeyboardSubstitutionCost) == 2
  }

  property("osaWithKeyboard counts substitutions correctly") {
    (osaWithSubstitutionCost("abc", "agc")(weightedKeyboardSubstitutionCost) * 100).round == 112
  }

  property("osaWithKeyboard counts transpositions correctly") {
    osaWithSubstitutionCost("abc", "acb")(weightedKeyboardSubstitutionCost) == 1
  }

  property("osaWithKeyboard with azerty works") {
    assertEquals(osaWithSubstitutionCost("mop", "nop")(qwertySubstitutionCost), 1D)
    assertEquals(osaWithSubstitutionCost("mop", "nop")(azertySubstitutionCost), 2D)
  }
}
