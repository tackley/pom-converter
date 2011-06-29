package net.tackley.pom

import xml.Elem

object Converter {
  def convert(elem: Elem): List[String] = {
    val props = Properties.fromPom(elem)
    val deps = Dependency.fromPom(elem).map(_.substituteProps(props))

    "libraryDependencies ++= Seq(" ::
      indentAndAddCommaToAllButLast(deps.map(_.asSbt)) :::
    ")" ::
    Nil
  }

  private def indentAndAddCommaToAllButLast(l: List[String]): List[String] = l match {
    case Nil => Nil
    case last :: Nil => "  " + last :: Nil
    case head :: tail => "  " + head + "," :: indentAndAddCommaToAllButLast(tail)
  }

}