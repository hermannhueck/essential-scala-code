class B {
  def bar = "This is the best method ever!"
}

class A

implicit def aToB(in: A): B = new B()

new A().bar





implicit def intToBoolean(int: Int) = int == 0

if(1) "yes" else "no"

if(0) "yes" else "no"
