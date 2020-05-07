package munit

class DisciplineSuiteTest extends DisciplineSuite {
  checkAll("Dummy", DummyLaws.dummy)
  checkAll("Dummy".ignore, DummyLaws.dummy)
}
