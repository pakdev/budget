import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

import com.thoughtworks.binding.{dom, Binding}
import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import org.scalajs.dom.{document, Event, Node}

@JSExportTopLevel("RenderProcess")
object RenderProcess {
  val data = Vars.empty[Contact]
  data.value += Contact(Var("Foo"), Var("Bar"))

  @dom
  def table: Binding[BindingSeq[Node]] = {
    <div>
      <button
        onclick={ event: Event =>
          data.value += Contact(Var("Pete"), Var("pkurlak@gmail.com"))
        }
      >
        Add a contact
      </button>
    </div>
    <table border="1" cellPadding="5">
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        {
          for (contact <- data) yield {
            <tr>
              <td>
                {contact.name.bind}
              </td>
              <td>
                {contact.email.bind}
              </td>
            </tr>
          }
        }
      </tbody>
    </table>
  }

  @JSExport
  def main(): Unit = {
    dom.render(document.body, table)
  }
}
