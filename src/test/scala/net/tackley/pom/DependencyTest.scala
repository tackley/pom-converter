package net.tackley.pom

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import xml.{Node, Elem}

class DependencyTest extends FlatSpec with ShouldMatchers {
  "Dependency parser" should "be able to process simple dependencies" in {
    val dependencies = Dependency.fromPom(ExamplePoms.simplePom)
    dependencies should be (List(
      Dependency("org.scala-lang", "scala-library", "2.8.1"),
      Dependency("org.scalatest", "scalatest", "1.3", scope = Some("test")),
      Dependency("net.sf.json-lib", "json-lib", "1.1", classifier = Some("jdk15"))
    ))
  }


  it should "be able to substitute properties" in {
    val props = Map("a" -> "newa", "b" -> "newb")

    Dependency("blah.blah", "artifact", "${a}").substituteProps(props) should be (
      Dependency("blah.blah", "artifact", "newa")
    )

    Dependency("blah.blah", "artifact", "${notknown}").substituteProps(props) should be (
      Dependency("blah.blah", "artifact", "${notknown}")
    )

  }


}