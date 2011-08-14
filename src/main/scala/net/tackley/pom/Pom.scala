package net.tackley.pom

import xml._
import java.io.File

class Pom(pom: Elem, parentProps: Map[String, String] = Map.empty) {

  def this(file: File, parentProps: Map[String, String]) = this(XML.loadFile(file), parentProps)

  lazy val dependencies = Dependency.fromPom(pom).map(_.substituteProps(properties))

  lazy val properties = parentProps ++ {
    val propNodes = (pom \ "properties").headOption.map(_.child).getOrElse(Nil)
    propNodes filter (_.isInstanceOf[Elem]) map (p => p.label -> p.text) toMap
  }

  lazy val moduleNames = (pom \\ "module") map (_.text) toSet

//  lazy val repositories =
}



