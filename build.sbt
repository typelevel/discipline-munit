val scala212 = "2.12.20"
val scala213 = "2.13.18"
val scala3   = "3.3.7"

val mUnit           = "1.0.0"
val mUnitScalaCheck = "1.0.0"
val discipline      = "1.7.0"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    tlBaseVersion      := "2.0",
    scalaVersion       := scala213,
    crossScalaVersions := Seq(scala212, scala213, scala3),
    licenses           := List(
      "BSD-3-Clause" -> url("http://opensource.org/licenses/BSD-3-Clause")
    ),
    startYear          := Some(2020),
    developers         := List(
      Developer(
        "rpiaggio",
        "Raúl Piaggio",
        "rpiaggio@gmail.com",
        url("http://github.com/rpiaggio")
      )
    )
  )
)

lazy val root = tlCrossRootProject.aggregate(core)

lazy val core = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .in(file("core"))
  .settings(
    name := "discipline-munit",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit"            % mUnit,
      "org.scalameta" %%% "munit-scalacheck" % mUnitScalaCheck,
      "org.typelevel" %%% "discipline-core"  % discipline
    )
  )
