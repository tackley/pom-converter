package net.tackley.pom

import xml._
import java.io.{FileWriter, File}

object Converter extends App {
  def convert(pom: Pom): List[String] = {

    "libraryDependencies ++= Seq(" ::
      indentAndAddCommaToAllButLast(pom.dependencies.map(_.asSbt)) :::
    ")" ::
    Nil
  }

  private def indentAndAddCommaToAllButLast(l: List[String]): List[String] = l match {
    case Nil => Nil
    case last :: Nil => "  " + last :: Nil
    case head :: tail => "  " + head + "," :: indentAndAddCommaToAllButLast(tail)
  }

  private def processDir(dir: File, parentProps: Map[String, String]) {
    println("Processing directory " + dir.getCanonicalPath)
    val pom = new Pom(new File(dir, "pom.xml"), parentProps)
    writeToFile(new File(dir, "build.sbt"), convert(pom))

    for (module <- pom.moduleNames) {
      processDir(new File(dir, module), pom.properties)
    }
  }

  private def writeToFile(file: File, content: List[String]) {
    val f = new FileWriter(file)
    content foreach { line => f.write(line); f.write("\n") }
    f.close()

    println("Generated " + file.getCanonicalPath)
  }

  processDir(new File(args.headOption.getOrElse(".")), Map.empty)

}