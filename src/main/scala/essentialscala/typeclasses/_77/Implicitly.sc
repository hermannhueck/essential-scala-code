case class Example(name: String)
implicit val implicitExample = Example("implicit")

implicitly[Example]

implicitly[Example] == implicitExample