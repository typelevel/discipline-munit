val mUnit      = "0.7.29"
val discipline = "1.1.5"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    tlBaseVersion       := "1.0",
    scalaVersion        := "2.13.6",
    crossScalaVersions  := Seq("2.12.15", "2.13.6", "3.0.2"),
    tlVersionIntroduced := Map("3" -> "1.0.9"),
    licenses            := List(
      "BSD-3-Clause" -> url("http://opensource.org/licenses/BSD-3-Clause")
    ),
    startYear           := Some(2020),
    developers          := List(
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
      "org.scalameta" %%% "munit-scalacheck" % mUnit,
      "org.typelevel" %%% "discipline-core"  % discipline
    )
  )
  .nativeSettings(
    tlVersionIntroduced ++= List("2.12", "2.13").map(_ -> "1.0.5").toMap,
    crossScalaVersions := (ThisBuild / crossScalaVersions).value.filter(_.startsWith("2."))
  )
