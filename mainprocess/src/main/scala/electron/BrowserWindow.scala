package electron

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import io.scalajs.nodejs.events.EventEmitter

@js.native
@JSImport("electron", "BrowserWindow")
class BrowserWindow(options: BrowserWindowOptions) extends EventEmitter {
  val webContents: WebContents = js.native

  def loadURL(url: String): Unit = js.native

}

trait BrowserWindowOptions extends js.Object {
  val width: js.UndefOr[Int] = js.undefined
  val height: js.UndefOr[Int] = js.undefined
}
