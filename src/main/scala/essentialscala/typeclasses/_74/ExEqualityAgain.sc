trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

final case class Person(name: String, email: String)

object EmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email
}

implicit object NameEmailEqual extends Equal[Person] {
  def equal(v1: Person, v2: Person): Boolean =
    v1.email == v2.email && v1.name == v2.name
}

object Eq {
  def apply[A](v1: A, v2: A)(implicit equal: Equal[A]) =
    equal.equal(v1, v2)
}

Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
