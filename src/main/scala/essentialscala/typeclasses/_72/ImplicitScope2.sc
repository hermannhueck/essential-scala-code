final case class Rational(numerator: Int, denominator: Int)

object Instance {
  implicit val ordering = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}

object Example {
  def example =
    assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
      List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
}

//Error:(12, 66) No implicit Ordering defined for A$A1.this.Rational.
//  assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
//    ^
