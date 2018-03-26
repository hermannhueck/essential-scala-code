
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
