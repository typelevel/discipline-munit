import sbtcrossproject.CrossPlugin.autoImport.crossProject

val mUnit      = "0.7.22"
val discipline = "1.1.4"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    name := "discipline-munit",
    organization := "org.typelevel",
    scalaVersion := "2.13.4",
    crossScalaVersions := Seq("2.13.5", "2.13.4", "3.0.0-M3", "3.0.0-RC1"),
    homepage := Some(url("https://github.com/typelevel/discipline-munit")),
    licenses += ("BSD 3-Clause", url(
      "http://opensource.org/licenses/BSD-3-Clause"
    )),
    developers := List(
      Developer(
        "rpiaggio",
        "Raúl Piaggio",
        "rpiaggio@gmail.com",
        url("http://github.com/rpiaggio")
      )
    )
  )
)

lazy val root = project
  .in(file("."))
  .aggregate(coreJVM, coreJS, coreNative)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .in(file("core"))
  .settings(
    moduleName := "discipline-munit",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit"            % mUnit,
      "org.scalameta" %%% "munit-scalacheck" % mUnit,
      "org.typelevel" %%% "discipline-core"  % discipline
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/typelevel/discipline-munit"),
        "scm:git:git@github.com:typelevel/discipline-munit.git",
        Some("scm:git:git@github.com:typelevel/discipline-munit.git")
      )
    ),
    testFrameworks += new TestFramework("munit.Framework"),
    pomIncludeRepository := { _ => false }
  )
  .jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) })
  .jvmSettings(
    Compile / doc / sources := {
      val old = (Compile / doc / sources).value
      if (isDotty.value)
        Seq()
      else
        old
    }
  )
  .nativeSettings(
    crossScalaVersions := crossScalaVersions.value.filter(_.startsWith("2."))
  )

lazy val coreJVM = core.jvm

lazy val coreJS = core.js

lazy val coreNative = core.native

sonatypeProfileName := "org.typelevel"

packagedArtifacts in root := Map.empty
