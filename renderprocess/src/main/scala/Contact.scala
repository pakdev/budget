import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{dom, Binding}
import org.scalajs.dom.html.Table

case class Contact(name: Var[String], email: Var[String])
