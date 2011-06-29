package net.tackley.pom

object ExamplePoms {
  val simplePom =
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>

      <properties>
        <scala.version>2.8.1</scala.version>
        <scalatest.version>1.2</scalatest.version>
        <spring.version>3.0.0.RELEASE</spring.version>
      </properties>

      <dependencies>
        <dependency>
          <groupId>org.scala-lang</groupId>
          <artifactId>scala-library</artifactId>
          <version>2.8.1</version>
        </dependency>

        <dependency>
          <groupId>org.scalatest</groupId>
          <artifactId>scalatest</artifactId>
          <version>1.3</version>
          <scope>test</scope>
        </dependency>
      </dependencies>
    </project>

  val pomWithPropeties =
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>

      <properties>
        <scala.version>2.8.1</scala.version>
        <scalatest.version>1.2</scalatest.version>
        <spring.version>3.0.0.RELEASE</spring.version>
      </properties>

      <dependencies>
        <dependency>
          <groupId>org.scala-lang</groupId>
          <artifactId>scala-library</artifactId>
          <version>${{scala.version}}</version>
        </dependency>

        <dependency>
          <groupId>org.scalatest</groupId>
          <artifactId>scalatest</artifactId>
          <version>${{scalatest.version}}</version>
          <scope>test</scope>
        </dependency>

        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-core</artifactId>
          <version>${{spring.version}}</version>
        </dependency>

      </dependencies>

    </project>


}