name         := "osita"
organization := "nl.gn0s1s"
startYear    := Some(2022)
homepage     := Some(url("https://github.com/philippus/osita"))
licenses += ("MPL-2.0", url("https://www.mozilla.org/MPL/2.0/"))

developers := List(
  Developer(
    id = "philippus",
    name = "Philippus Baalman",
    email = "",
    url = url("https://github.com/philippus")
  )
)

scalaVersion := "3.3.8"
crossScalaVersions += "2.13.18"

ThisBuild / versionScheme          := Some("semver-spec")
ThisBuild / versionPolicyIntention := Compatibility.None

Compile / packageBin / packageOptions += Package.ManifestAttributes(
  "Automatic-Module-Name" -> "nl.gn0s1s.osita"
)

scalacOptions += "-deprecation"

libraryDependencies ++= Seq(
  "org.scalameta" %% "munit"            % "1.3.4" % Test,
  "org.scalameta" %% "munit-scalacheck" % "1.3.0" % Test
)
