package net.tackley.pom

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class ConverterTest extends FlatSpec with ShouldMatchers {
  "converter" should "be able to convert simple pom" in {
    val result = Converter.convert(new Pom(ExamplePoms.simplePom))
    result should be (
      """libraryDependencies ++= Seq(""" ::
      """  "org.scala-lang" % "scala-library" % "2.8.1",""" ::
      """  "org.scalatest" % "scalatest" % "1.3" % "test",""" ::
      """  "net.sf.json-lib" % "json-lib" % "1.1" classifier "jdk15"""" ::
      """)""" ::
      Nil
   )
  }


  it should "perform property replacement" in {
    val result = Converter.convert(new Pom(ExamplePoms.pomWithPropeties))
    result should be (
      """libraryDependencies ++= Seq(""" ::
      """  "org.scala-lang" % "scala-library" % "2.8.1",""" ::
      """  "org.scalatest" % "scalatest" % "1.2" % "test",""" ::
      """  "org.springframework" % "spring-core" % "3.0.0.RELEASE"""" ::
      """)""" ::
      Nil
   )

  }


}