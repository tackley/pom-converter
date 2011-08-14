package net.tackley.pom

import xml._
import collection.immutable.Map
import java.io.File

case class Dependency(groupId: String, artifactId: String, version: String, scope: Option[String] = None, classifier: Option[String] = None) {
  def substituteProps(props: Map[String, String]) = {
    val Property = """^\$\{(.*)\}$""".r

    def subst(s: String) = s match {
      case Property(name) => props.getOrElse(name, s)
      case other => other
    }

    this.copy(version = subst(version))
  }

  def asSbt = quote(groupId) + " % " + quote(artifactId) + " % " + quote(version) + optScope + optClassifier

  private lazy val optScope = scope map { " % " + quote(_) } getOrElse ""
  private lazy val optClassifier = classifier map { " classifier " + quote(_) } getOrElse ""
  private def quote(s: String) = "\"" + s + "\""
}

object Dependency {
  def fromXml(n: Node) =
    Dependency(
      groupId = (n \ "groupId").text,
      artifactId = (n \ "artifactId").text,
      version = (n \ "version").text,
      scope = Option((n \ "scope").text).filter(!_.isEmpty),
      classifier = Option((n \ "classifier").text).filter(!_.isEmpty)
    )

  def fromPom(pom: NodeSeq) = pom \\ "dependency" map fromXml toList
}




