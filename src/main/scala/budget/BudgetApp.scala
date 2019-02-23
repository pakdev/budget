package budget

import electron.{BrowserWindow, ElectronApp}

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{literal => JsObject}

class BudgetApp(dirName: String, require: js.Function1[String, js.Any]) extends ElectronApp(require) {
  private var mainWindow: Option[BrowserWindow] = None

  electronApp.onceReady(createWindow _)

  process.platform.asInstanceOf[String] match {
    case "darwin" =>
      electronApp onActivate { () =>
        if (mainWindow.isEmpty) {
          createWindow()
        }
      }
    case _ =>
      electronApp onWindowAllClosed { () => electronApp.quit() }
  }

  private def createWindow(): Unit = {
    mainWindow = Some(BrowserWindow(JsObject(width = 600, height = 600)))
    mainWindow.foreach { window =>
      window.loadURL(s"file://$dirName/index.html")

      window.webContents.openDevTools()

      window.on("closed") { () => mainWindow = None }
    }
  }
}
