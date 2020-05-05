package org.typelevel.discipline
package munit

import _root_.munit.Location
import _root_.munit.ScalaCheckSuite

trait Discipline extends ScalaCheckSuite {

  def checkAll(name: String, ruleSet: Laws#RuleSet)(implicit
    loc:             Location
  ) =
    ruleSet.all.properties.toList.foreach {
      case (id, prop) =>
        property(s"$name: $id") {
          prop
        }
    }

}
