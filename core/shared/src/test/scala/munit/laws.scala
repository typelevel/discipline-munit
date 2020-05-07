package munit

import org.scalacheck.Prop
import org.typelevel.discipline.Laws

object Dummy {
  def prop  = Prop(_ => Prop.Result(status = Prop.True))
  def prop2 = Prop(true)
}

object DummyLaws extends Laws {
  def dummy =
    new DefaultRuleSet(
      name = "dummy",
      parent = None,
      "true"     -> Dummy.prop,
      "alsoTrue" -> Dummy.prop2
    )
}
