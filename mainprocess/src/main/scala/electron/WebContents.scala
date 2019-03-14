package electron

import scala.scalajs.js

@js.native
trait WebContents extends js.Object {
  def openDevTools(): Unit = js.native
}
