// See README.md for license details.

ThisBuild / scalaVersion     := "2.13.18"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "page.gwlab"

val chiselVersion = "7.6.0"

lazy val root = (project in file("."))
  .settings(
    name := "DEMO",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "org.scalatest" %% "scalatest" % "3.2.19" % "test",
    ),
    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations",
    ),
    addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % chiselVersion cross CrossVersion.full),
  )
