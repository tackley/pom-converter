package net.tackley.pom

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import xml.{Node, Elem}

class SimpleDependencyTest extends FlatSpec with ShouldMatchers {
  val pom = ExamplePoms.simplePom

  "pom parser" should "be able to process simple dependencies" in {
    val dependencies = Dependency.fromPom(pom)
    dependencies should be (List(
      Dependency("org.scala-lang", "scala-library", "2.8.1"),
      Dependency("org.scalatest", "scalatest", "1.3", Some("test"))
    ))
  }


  it should "be able to parse properties" in {
    val properties = Properties.fromPom(pom)

    properties should be (Map(
      "scala.version" -> "2.8.1",
      "scalatest.version" -> "1.2",
      "spring.version" -> "3.0.0.RELEASE"
    ))
  }

  it should "be able to substitute properties" in {
    val props = Map("a" -> "newa", "b" -> "newb")

    Dependency("blah.blah", "artifact", "${a}").substituteProps(props) should be (
      Dependency("blah.blah", "artifact", "newa")
    )
  }

}