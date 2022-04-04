# osita

[![build](https://github.com/Philippus/osita/workflows/build/badge.svg)](https://github.com/Philippus/osita/actions/workflows/scala.yml?query=workflow%3Abuild+branch%3Amain)
[![codecov](https://codecov.io/gh/Philippus/osita/branch/main/graph/badge.svg)](https://codecov.io/gh/Philippus/osita)
![Current Version](https://img.shields.io/badge/version-0.0.1-brightgreen.svg?style=flat "0.0.1")
[![Scala Steward badge](https://img.shields.io/badge/Scala_Steward-helping-blue.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=)](https://scala-steward.org)
[![license](https://img.shields.io/badge/license-MPL%202.0-blue.svg?style=flat "MPL 2.0")](LICENSE)

Osita is an implementation of the [Optimal String Alignment distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#Optimal_string_alignment_distance)
algorithm. It implements the standard version of the algorithm and an extension of it where the substitution cost has
been replaced by a function which calculates the keyboard distance between characters using the Euclidean distance
between keys on a QWERTY-keyboard.
You can also supply your own substitution cost function.

## Installation
Osita is published for Scala 2.13. To start using it add the following to your `build.sbt`:

```
libraryDependencies += "nl.gn0s1s" %% "osita" % "0.0.1"
```

## Example usage

```scala
import nl.gn0s1s.osita.Osita._

osa("abcde", "abcde") // val res0: Double = 0.0
osa("abcde", "abcd") // val res1: Double = 1.0
osaWithSubstitutionCost("abc", "agc")(weightedKeyboardSubstitutionCost) // val res2: Double = 1.118033988749895

```

## Resources
- [Optimal String Alignment distance](https://en.wikipedia.org/wiki/Damerau%E2%80%93Levenshtein_distance#Optimal_string_alignment_distance)
- [Euclidean Distance](https://en.wikipedia.org/wiki/Euclidean_distance)
- Distances between keys on a QWERTY keyboard on Code Golf - https://codegolf.stackexchange.com/questions/233618/distances-between-keys-on-a-qwerty-keyboard
- Keyboard distance in Perl - https://metacpan.org/release/KRBURTON/String-KeyboardDistance-1.01/source/README

## License
The code is available under the [Mozilla Public License, version 2.0](LICENSE).
