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

        <dependency>
            <groupId>net.sf.json-lib</groupId>
            <artifactId>json-lib</artifactId>
            <version>1.1</version>
            <classifier>jdk15</classifier>
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


  val pomWithProfile =
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>

      <profiles>
        <profile>
          <id>continuous-integration</id>
          <properties>
            <database.load.mode>auto</database.load.mode>
          </properties>
        </profile>
      </profiles>

      <properties>
        <scala.version>2.8.1</scala.version>
      </properties>

    </project>

  val pomWithSubmodules =
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                          http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <profiles>
        <profile>
          <id>full-build</id>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
          <modules>
            <module>a</module>
            <module>b</module>
            <module>c</module>
          </modules>
        </profile>
        <profile>
          <id>ftr-build</id>
          <modules>
            <module>b</module>
            <module>d</module>
          </modules>
        </profile>
      </profiles>
    </project>
}