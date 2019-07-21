name := "topcoder_parser"

version := "0.1"

scalaVersion := "2.12.8"
//libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.35.0"
//libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
// https://mvnrepository.com/artifact/org.jsoup/jsoup
libraryDependencies += "org.jsoup" % "jsoup" % "1.12.1"
libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.1",
  "commons-io" % "commons-io" % "2.4"
)


