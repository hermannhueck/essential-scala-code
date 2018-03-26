
object IntImplicits {

  implicit class IntOps(n: Int) {
    def yeah = for {
      _ <- 0 until n
    } println("Oh yeah!")
  }

}

import IntImplicits._

2.yeah
3.yeah
-1.yeah
