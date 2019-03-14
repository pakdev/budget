package mainprocess

import scala.collection.mutable
import scala.scalajs.js.UndefOr

import electron.{App, BrowserWindow, BrowserWindowOptions}
import io.scalajs.nodejs.process

object MainProcess {
  val windows: mutable.Set[BrowserWindow] = mutable.Set()

  def createWindow(): Unit = {
    println("hello")

    val window = new BrowserWindow(
      new BrowserWindowOptions {
        override val height: UndefOr[Int] = 600
        override val width: UndefOr[Int] = 800
      }
    )

    window.loadURL(s"file://${process.cwd()}/electron/index.html")

    if (scala.scalajs.LinkingInfo.developmentMode) {
      window.webContents.openDevTools()
    }

    window.on("closed", () => {
      windows -= window
    })

    windows += window
  }

  def main(args: Array[String]): Unit = {
    App.on("ready", () => createWindow())
    App.on("window-all-closed", () => App.quit())
  }
}
