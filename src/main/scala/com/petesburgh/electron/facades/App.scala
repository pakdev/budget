package com.petesburgh.electron.facades

import io.scalajs.nodejs.events.EventEmitter

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("electron", "app")
object App extends EventEmitter {
  def quit(): Unit = js.native
}
