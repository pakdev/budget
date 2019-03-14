package electron

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import io.scalajs.nodejs.events.EventEmitter

@js.native
@JSImport("electron", "app")
object App extends EventEmitter {
  def quit(): Unit = js.native
}
