
object IntImplicits {

  implicit class IntOps(n: Int) {
    def yeah =
      times(_ => println("Oh yeah!"))

    def times(func: Int => Unit) =
      for (i <- 0 until n) func(i)
  }

}

import IntImplicits._

2.yeah
3.yeah
-1.yeah

3.times(i => println(s"Look - it's the number $i!"))
