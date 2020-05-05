package org.typelevel.discipline.munit

import munit.ScalaCheckSuite

class DisciplineTest extends ScalaCheckSuite with Discipline {
  checkAll("Dummy", DummyLaws.dummy)
}
