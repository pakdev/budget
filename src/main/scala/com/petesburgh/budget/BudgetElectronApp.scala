package com.petesburgh.budget

import com.petesburgh.electron.ElectronApp
import com.petesburgh.electron.facades.{BrowserWindow, BrowserWindowOptions}
import io.scalajs.nodejs.process

object BudgetElectronApp extends ElectronApp {
  app.on(
    "ready", { () =>
      val win = new BrowserWindow(new BrowserWindowOptions {
        val width = 800
        val height = 600
      })

      win.loadURL(s"file://${process.cwd()}/web/index.html")
    }
  )
}
