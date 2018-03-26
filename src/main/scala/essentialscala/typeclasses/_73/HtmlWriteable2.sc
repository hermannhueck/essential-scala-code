import java.util.Date

trait HtmlWriteable {
  def toHtml: String
}

final case class Person(name: String, email: String)

object HtmlWriter {
  def write(in: Any): String =
    in match {
      case Person(name, email) => s"<span>$name &lt;$email&gt;</span>"
      case Date => s"<span>${in.toString}</span>"
      case _ => throw new Exception(s"Can't render ${in} to HTML")
    }
}