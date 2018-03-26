
object IntImplicits {

  class IntOps(n: Int) {
    def yeah =
      times(_ => println("Oh yeah!"))

    def times(func: Int => Unit) =
      for (i <- 0 until n) func(i)
  }

  implicit def intToIntOps(value: Int) =
    new IntOps(value)

}

import IntImplicits._

5.yeah
