name := "ScalaWiremock"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "com.github.tomakehurst" % "wiremock-jre8" % "2.33.2",
  "org.scalatest"         %% "scalatest"     % "3.2.12",
  "com.typesafe.akka"     %% "akka-http"     % "10.2.9",
  "com.typesafe.akka"     %% "akka-actor"    % "2.6.19",
  "com.typesafe.akka"     %% "akka-stream"   % "2.6.19",
  "com.github.pureconfig" %% "pureconfig"    % "0.17.1"
).map(_ % Test)
