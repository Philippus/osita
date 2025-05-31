package nl.gn0s1s.osita

import scala.math.min

object Osita {
  // Create a matrix of a physical QWERTY-keyboard, so we can calculate the distance between keys based on the
  // coordinates, inspired by https://metacpan.org/release/KRBURTON/String-KeyboardDistance-1.01/source/README
  private val qwertyKeyboardGrid: Array[Array[Char]] = {
    """qwertyuiop
      |asdfghjkl
      |zxcvbnm""".stripMargin
      .split('\n')
      .map(_.toCharArray)
  }

  private val azertyKeyboardGrid: Array[Array[Char]] = {
    """azertyuiop
      |qsdfghjklm
      |wxcvbn""".stripMargin
      .split('\n')
      .map(_.toCharArray)
  }

  private def simpleSubstitutionCost[A](a: A, b: A): Double =
    if (a == b) 0.0D else 1.0D

  private def findPos(c: Char, keyboardGrid: Array[Array[Char]]): (Double, Double) = {
    val t      = keyboardGrid.map(row => row.indexOf(c))
    val column = t.max
    val row    = t.indexOf(column)
    // compensate for the difference between rows on a keyboard
    // idea taken from https://codegolf.stackexchange.com/questions/233618/distances-between-keys-on-a-qwerty-keyboard
    val factor = row match {
      case 0 => 0.0D
      case 1 => 0.25D
      case 2 => 0.75D
    }
    (column + factor, row)
  }

  def weightedKeyboardSubstitutionCost(a: Char, b: Char): Double = {
    // https://en.wikipedia.org/wiki/Euclidean_distance
    def euclideanDistance(p1: (Double, Double), p2: (Double, Double)): Double =
      scala.math.sqrt(scala.math.pow(p1._1 - p2._1, 2) + scala.math.pow(p1._2 - p2._2, 2))

    euclideanDistance(findPos(a, qwertyKeyboardGrid), findPos(b, qwertyKeyboardGrid))
  }

  def qwertySubstitutionCost(a: Char, b: Char): Double = {
    def euclideanDistance(p1: (Double, Double), p2: (Double, Double)): Double =
      scala.math.sqrt(scala.math.pow(p1._1 - p2._1, 2) + scala.math.pow(p1._2 - p2._2, 2))

    euclideanDistance(findPos(a, qwertyKeyboardGrid), findPos(b, qwertyKeyboardGrid))
  }

  def azertySubstitutionCost(a: Char, b: Char): Double = {
    def euclideanDistance(p1: (Double, Double), p2: (Double, Double)): Double =
      scala.math.sqrt(scala.math.pow(p1._1 - p2._1, 2) + scala.math.pow(p1._2 - p2._2, 2))

    euclideanDistance(findPos(a, azertyKeyboardGrid), findPos(b, azertyKeyboardGrid))
  }

  // https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#cite_note-Boytsov-7

  def osa[A](a: Seq[A], b: Seq[A]): Double =
    osaWithSubstitutionCost(a, b)(simpleSubstitutionCost)

  def osaWithSubstitutionCost[A](a: Seq[A], b: Seq[A])(substitutionCost: (A, A) => Double): Double = {
    val deletionCost      = 1.0D
    val insertionCost     = 1.0D
    val transpositionCost = 1.0D

    val d = Array.ofDim[Double](a.size + 1, b.size + 1)

    for (i <- 0 to a.size)
      d(i)(0) = i * deletionCost
    for (j <- 0 to b.size)
      d(0)(j) = j * insertionCost
    for {
      i <- 1 to a.size
      j <- 1 to b.size
    } {
      d(i)(j) = min(
        min(
          d(i - 1)(j) + deletionCost, // deletion
          d(i)(j - 1) + insertionCost
        ), // insertion
        d(i - 1)(j - 1) + substitutionCost(a(i - 1), b(j - 1))
      )    // substitution
      if (i > 1 && j > 1 && a(i - 1) == b(j - 2) && a(i - 2) == b(j - 1))
        d(i)(j) = min(d(i)(j), d(i - 2)(j - 2) + transpositionCost) // transposition
    }
    d(a.size)(b.size)
  }
}
