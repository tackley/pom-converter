package net.tackley.pom

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class PomTest extends FlatSpec with ShouldMatchers {

  "Pom class" should "be able to parse properties" in {
    val pom = new Pom(ExamplePoms.simplePom)

    pom.properties should be (Map(
      "scala.version" -> "2.8.1",
      "scalatest.version" -> "1.2",
      "spring.version" -> "3.0.0.RELEASE"
    ))
  }

  it should "ignore profile settings during property reading" in {
    val pom = new Pom(ExamplePoms.pomWithProfile)

    pom.properties should be (Map(
      "scala.version" -> "2.8.1"
    ))
  }

  it should "retrieve module names" in {
    val pom = new Pom(ExamplePoms.pomWithSubmodules)

    pom.moduleNames should be (Set("a", "b", "c", "d"))
  }



}