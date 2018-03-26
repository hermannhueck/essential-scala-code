
trait HtmlWriter[A] {
  def write(in: A): String
}

final case class Person(name: String, email: String)

implicit object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

object HtmlUtil {
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = {
    writer.write(data)
  }
}

HtmlUtil.htmlify(Person("John", "john@example.com"))(PersonWriter)
HtmlUtil.htmlify(Person("John", "john@example.com")) // use implicit PersonWriter

implicit object ApproximationWriter extends HtmlWriter[Int] {
  def write(in: Int): String =
    s"It's definitely less than ${((in / 10) + 1) * 10}"
}

HtmlUtil.htmlify(2) // use implicit ApproximationWriter
