import sbtcrossproject.CrossPlugin.autoImport.crossProject

val scalaJSVersion06 = Option(System.getenv("SCALAJS_VERSION")).exists(_.startsWith("0.6"))

val mUnit      = "0.7.10"
val discipline = "1.0.3"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    name := "discipline-munit",
    organization := "org.typelevel",
    scalaVersion := "2.13.2",
    crossScalaVersions := Seq("2.11.12", "2.12.11", scalaVersion.value),
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
  .aggregate(coreJVM, coreJS)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val core = crossProject(JVMPlatform, JSPlatform)
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
  .jvmSettings(
    skip.in(publish) := scalaJSVersion06
  )
  .jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
  )

lazy val coreJVM = core.jvm

lazy val coreJS = core.js

sonatypeProfileName := "org.typelevel"

packagedArtifacts in root := Map.empty
