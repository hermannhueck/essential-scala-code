trait Equal[A] {
  def equal(v1: A, v2: A): Boolean
}

final case class Person(name: String, email: String)

object Eq {
  def apply[A](v1: A, v2: A)(implicit equal: Equal[A]) =
    equal.equal(v1, v2)
}

object EmailImplicit {
  implicit object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email
  }
}

object NameAndEmailImplicit {
  implicit object NameEmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email && v1.name == v2.name
  }
}

object Examples {
  def byNameAndEmail = {
    import NameAndEmailImplicit._
    Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
  }

  def byEmail = {
    import EmailImplicit._
    Eq(Person("Noel", "noel@example.com"), Person("Dave", "noel@example.com"))
  }
}

Examples.byNameAndEmail
Examples.byEmail