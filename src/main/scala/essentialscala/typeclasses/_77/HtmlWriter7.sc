
trait HtmlWriter[A] {
  def write(in: A): String
}

object HtmlWriter {
/*
  def write[A](in: A)(implicit writer: HtmlWriter[A]): String =
    writer.write(in)
*/

  def apply[A](implicit instance: HtmlWriter[A]): HtmlWriter[A] =
    instance
}

final case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

HtmlWriter[Person].write(Person("Noel", "noel@example.org"))

implicit class HtmlOps[T](data: T) {
  def toHtml(implicit writer: HtmlWriter[T]) =
    writer.write(data)
}

Person("Noel", "noel@example.org").toHtml

// using implicit HtmlWriter
def pageTemplate[A](body: A)(implicit writer: HtmlWriter[A]): String = {
  val renderedBody = body.toHtml

  s"<html><head>...</head><body>${renderedBody}</body></html>"
}

pageTemplate2(Person("Noel", "noel@example.org"))

// using context bound   A : HtmlWriter
def pageTemplate2[A : HtmlWriter](body: A): String = {
  val renderedBody = body.toHtml

  s"<html><head>...</head><body>${renderedBody}</body></html>"
}

pageTemplate2(Person("Noel", "noel@example.org"))