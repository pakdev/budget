package nodejs

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("url", JSImport.Namespace)
object URL extends js.Object {
  def format(options: FormatOptions): String = js.native
}

trait FormatOptions extends js.Object {
  val pathname: js.UndefOr[String] = js.undefined
  val protocol: js.UndefOr[String] = js.undefined
  val slashes: js.UndefOr[Boolean] = js.undefined
}
