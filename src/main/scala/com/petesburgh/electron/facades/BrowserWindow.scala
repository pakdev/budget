package com.petesburgh.electron.facades

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("electron", "BrowserWindow")
class BrowserWindow(options: BrowserWindowOptions) extends js.Object {

  def loadURL(url: String): Unit = js.native
}
