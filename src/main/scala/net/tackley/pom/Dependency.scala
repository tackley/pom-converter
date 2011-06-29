package net.tackley.pom

import xml._
import collection.immutable.Map

case class Dependency(groupId: String, artifactId: String, version: String, scope: Option[String] = None) {
  def substituteProps(props: Map[String, String]) = {
    val Property = """^\$\{(.*)\}$""".r

    def subst(s: String) = s match {
      case Property(name) => props.getOrElse(name, s)
      case other => other
    }

    this.copy(
      version = subst(version)
    )
  }

  def asSbt = quote(groupId) + " % " + quote(artifactId) + " % " + quote(version) + optScope

  lazy val optScope = scope map { " % " + quote(_) } getOrElse ""

  private def quote(s: String) = "\"" + s + "\""
}

object Dependency {
  def fromXml(n: Node) = {
    Dependency(
      groupId = (n \ "groupId").text,
      artifactId = (n \ "artifactId").text,
      version = (n \ "version").text,
      scope = Option((n \ "scope").text).filter(!_.isEmpty)
    )
  }

  def fromPom(pom: NodeSeq) = pom \\ "dependency" map fromXml toList
}

object Properties {
  def fromPom(pom: Elem) = {
    val properties = (pom \\ "properties").headOption.map(_.child).getOrElse(Nil)
    properties filter (_.isInstanceOf[Elem]) map (p => p.label -> p.text) toMap
  }

}


