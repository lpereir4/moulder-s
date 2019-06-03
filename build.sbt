
name := "moulder-s"

organization := "moulder"

scalaVersion := "2.12.8"

version := "1.0"

libraryDependencies ++= Seq(
	"org.jsoup" % "jsoup" % "1.12.1",
	"xmlunit" % "xmlunit" % "1.1",
	"org.specs2" %% "specs2-core" % "4.3.4" % "test",
	"org.specs2" %% "specs2-mock" % "4.5.1" % "test"
)

scalacOptions ++= "-unchecked"::"-deprecation"::"-feature"::Nil
