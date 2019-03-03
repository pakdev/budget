import org.scalajs.core.tools.linker.backend.ModuleKind.CommonJSModule

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

name := "budget"
organization := "com.petesburgh"
version := "0.1"

scalaVersion := "2.12.8"

// Compiler flags
scalacOptions ++= Seq(
  "-P:scalajs:sjsDefinedByDefault",
  "-feature",
  "-Ypartial-unification",
  "-Ywarn-value-discard"
)

// Add repositories to pull from
resolvers ++= Seq(
  Resolver.sonatypeRepo("releases")
)

// Libraries
libraryDependencies ++= Seq(
  "io.scalajs" %%% "nodejs" % "0.4.2",
)

// Javascript dependencies e.g.
// npmDependencies in Compile += "snabbdom" -> "0.5.3"

// Resources are in web directory
resourceDirectory := baseDirectory.value / "web"

// Write files to to web/js
artifactPath in (Compile, fastOptJS) := resourceDirectory.value / "js" / "budget-fastopt.js"
artifactPath in (Compile, fullOptJS) := resourceDirectory.value / "js" / "budget-fullopt.js"
artifactPath in (Compile, packageJSDependencies) := resourceDirectory.value / "js" / "budget-jsdeps.js"

// Put all js dependencies into a single output file
skip in packageJSDependencies := false

// Call the `main` method after the js is loaded
scalaJSUseMainModuleInitializer := true

// Do not emit source maps in production
emitSourceMaps in fullOptJS := false

// Import from CommonJS modules
scalaJSModuleKind := CommonJSModule

// Produce both application and library(s)
webpackBundlingMode := BundlingMode.LibraryAndApplication()

webpackConfigFile := Some(baseDirectory.value / "src" / "main" / "resources" / "no-electron.webpack.config.js")
