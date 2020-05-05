import sbtcrossproject.CrossPlugin.autoImport.crossProject

val scalaJSVersion06 = Option(System.getenv("SCALAJS_VERSION")).exists(_.startsWith("0.6"))

val mUnit      = "0.7.5"
val discipline = Option("1.0.2").filterNot(_ => scalaJSVersion06).getOrElse("1.0.1")

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    name := "discipline-munit",
    scalaVersion := "2.13.2",
    crossScalaVersions := Seq("2.11.12", "2.12.10", scalaVersion.value),
    organization := "com.rpiaggio",
    homepage := Some(url("https://github.com/rpiaggio/discipline-munit")),
    licenses += ("BSD 3-Clause", url(
      "http://opensource.org/licenses/BSD-3-Clause"
    )),
    developers := List(
      Developer(
        "rpiaggio",
        "Raúl Piaggio",
        "rpiaggio@gmail.com",
        url("http://rpiaggio.com")
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
        url("https://https://github.com/rpiaggio/discipline-munit"),
        "scm:git:git@github.com:rpiaggio/discipline-munit.git",
        Some("scm:git:git@github.com:rpiaggio/discipline-munit.git")
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

sonatypeProfileName := "com.rpiaggio"

packagedArtifacts in root := Map.empty
