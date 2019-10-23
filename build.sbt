name := "ChangeOverTime"

val copyMainProcess = taskKey[File]("Return main process fast compiled file directory.")
val copyRenderProcess = taskKey[File]("Return render process compiled file directory.")
lazy val fastOptCompileCopy = taskKey[Unit]("Compile and copy paste projects and generate corresponding json file.")

val copyMainProcessFullOpt = taskKey[File]("Return main process full compiled file directory.")
val copyRenderProcessFullOpt = taskKey[File]("Return render process full compiled file directory.")
lazy val fullOptCompileCopy = taskKey[Unit]("Compile and copy paste projects, and generate corresponding json file.")

fastOptCompileCopy := {
  val mainProcessDirectory = (copyMainProcess in `mainProcess`).value
  IO.delete(baseDirectory.value / "electron/mainprocess")
  IO.copyDirectory(
    mainProcessDirectory.getParentFile,
    baseDirectory.value / "electron/mainprocess",
    overwrite = true
  )

  val renderProcessDirectory = (copyRenderProcess in `renderProcess`).value
  IO.delete(baseDirectory.value / "electron/renderprocess")
  IO.copyDirectory(
    renderProcessDirectory.getParentFile,
    baseDirectory.value / "electron/renderprocess",
    overwrite = true
  )

  def toFastOptFile(line: String): String = """COMPILEMODE""".r.replaceAllIn(line, "fastopt")

  val sourcePackageJSON = IO.readLines(baseDirectory.value / "source-package-json/package.json")

  IO.writeLines(
    baseDirectory.value / "electron/package.json",
    sourcePackageJSON.map(toFastOptFile)
  )
}

fullOptCompileCopy := {
  val mainProcessDirectory = (copyMainProcessFullOpt in `mainProcess`).value
  IO.delete(baseDirectory.value / "electron/mainprocess")
  IO.copyDirectory(
    mainProcessDirectory.getParentFile,
    baseDirectory.value / "electron/mainprocess",
    overwrite = true
  )

  val renderProcessDirectory = (copyRenderProcessFullOpt in `renderProcess`).value
  IO.delete(baseDirectory.value / "electron/renderprocess")
  IO.copyDirectory(
    renderProcessDirectory.getParentFile,
    baseDirectory.value / "electron/renderprocess",
    overwrite = true
  )

  def toFullOptFile(line: String): String = """COMPILEMODE""".r.replaceAllIn(line, "opt")

  val sourcePackageJSON = IO.readLines(baseDirectory.value / "source-package-json/package.json")

  IO.writeLines(
    baseDirectory.value / "electron/package.json",
    sourcePackageJSON.map(toFullOptFile)
  )
}

val commonSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.8",
  scalacOptions ++= Seq("-P:scalajs:sjsDefinedByDefault")
)

lazy val mainProcess = project.in(file("./mainprocess"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    resolvers += Resolver.sonatypeRepo("releases"),
    libraryDependencies ++= Seq(
      "io.scalajs" %%% "nodejs" % "0.4.2"
    ),
    scalaJSUseMainModuleInitializer := true, 
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    copyMainProcess := {
      (fastOptJS in Compile).value.data
    },
    copyMainProcessFullOpt := {
      (fullOptJS in Compile).value.data
    }
  )

lazy val renderProcess = project.in(file("./renderprocess"))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.thoughtworks.binding" %%% "dom" % "latest.release"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
    copyRenderProcess := {
      (fastOptJS in Compile).value.data
    },
    copyRenderProcessFullOpt := {
      (fullOptJS in Compile).value.data
    }
  )

