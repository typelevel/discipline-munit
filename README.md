# discipline-munit - MUnit binding for Typelevel Discipline

![Build Status](https://github.com/typelevel/discipline-munit/actions/workflows/ci.yml/badge.svg) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.typelevel/discipline-munit_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.typelevel/discipline-munit_2.12)

## Quick Start

To use discipline-munit in an existing SBT project with Scala 2.12 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "org.typelevel" %%% "discipline-munit" % "<version>" % Test
)
```