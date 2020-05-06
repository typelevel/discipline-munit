package org.typelevel.discipline
package munit

import _root_.munit.Location
import _root_.munit.ScalaCheckSuite
import _root_.munit.TestOptions

trait Discipline extends ScalaCheckSuite {

  def checkAll(name: String, ruleSet: Laws#RuleSet)(implicit
    loc:             Location
  ): Unit = checkAll(new TestOptions(name, Set.empty, loc), ruleSet)

  def checkAll(options: TestOptions, ruleSet: Laws#RuleSet)(implicit
    loc:                Location
  ): Unit =
    ruleSet.all.properties.toList.foreach {
      case (id, prop) =>
        property(options.withName(s"${options.name}: $id")) {
          prop
        }
    }

}
