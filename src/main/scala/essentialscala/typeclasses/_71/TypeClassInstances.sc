// 7.1.1 Ordering
object _Ordering {
  val minOrdering = Ordering.fromLessThan[Int](_ < _)

  val maxOrdering = Ordering.fromLessThan[Int](_ > _)

  List(3, 4, 2).sorted(minOrdering)

  List(3, 4, 2).sorted(maxOrdering)
}

// 7.1.2 Implicit Values
object _ImplicitValues {
  implicit val ordering = Ordering.fromLessThan[Int](_ < _)

  List(2, 4, 3).sorted

  List(1, 7, 5).sorted
}

// 7.1.4 Implicit Value Ambiguity
object _ImplicitValueAmbiguity {
  implicit val minOrdering2 = Ordering.fromLessThan[Int](_ < _)

  implicit val maxOrdering2 = Ordering.fromLessThan[Int](_ > _)

  //--> List(3,4,5).sorted
  //  <console>:12: error: ambiguous implicit values:
  //  both value ordering of type => scala.math.Ordering[Int]
  //  and value minOrdering of type => scala.math.Ordering[Int]
  //  match expected type scala.math.Ordering[Int]
  //                 List(3,4,5).sorted
  //                             ^
}

// 7.1.6 Exercises
object _Exercises {
  implicit val absOrdering = Ordering.fromLessThan[Int] {
    Math.abs(_) < Math.abs(_)
  }

  assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
  assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))

  final case class Rational(numerator: Int, denominator: Int)

  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )

  assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
    List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
}

