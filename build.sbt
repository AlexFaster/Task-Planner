name := """ToDoListBackend"""
organization := "com.dreamteam"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.6.0"
libraryDependencies += "org.webjars" % "webjars-play_2.12" % "2.6.2"
libraryDependencies += "org.webjars" % "swagger-ui" % "3.2.0"



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.dreamteam.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.dreamteam.binders._"

herokuAppName in Compile := "secure-sierra-47475"