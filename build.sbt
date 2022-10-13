val mUnit      = "1.0.0-M6"
val discipline = "1.5.1"

Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    tlBaseVersion      := "2.0",
    scalaVersion       := "2.13.10",
    crossScalaVersions := Seq("2.12.17", "2.13.10", "3.1.3"),
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
      "org.scalameta" %%% "munit-scalacheck" % mUnit,
      "org.typelevel" %%% "discipline-core"  % discipline
    )
  )
