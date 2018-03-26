import java.util.Date

sealed trait JsValue {
  def stringify: String
}

final case class JsObject(values: Map[String, JsValue]) extends JsValue {
  def stringify = values
    .map { case (name, value) => "\"" + name + "\":" + value.stringify }
    .mkString("{", ",", "}")
}

final case class JsString(value: String) extends JsValue {
  def stringify = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
}

val js = JsObject(Map("foo" -> JsString("a"), "bar" -> JsString("b"), "baz" -> JsString("c")))

js.stringify

//----
trait JsWriter[A] {
  def write(value: A): JsValue
}

object JsUtil {
  def toJson[A](value: A)(implicit writer: JsWriter[A]) =
    writer write value
}

//----
sealed trait Visitor {
  def id: String

  def createdAt: Date

  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
                            val id: String,
                            val createdAt: Date = new Date()
                          ) extends Visitor

final case class User(
                       val id: String,
                       val email: String,
                       val createdAt: Date = new Date()
                     ) extends Visitor

implicit object AnonymousWriter extends JsWriter[Anonymous] {
  def write(value: Anonymous) = JsObject(Map(
    "id"           -> JsString(value.id),
    "createdAt"    -> JsString(value.createdAt.toString)
  ))
}

implicit object UserWriter extends JsWriter[User] {
  def write(value: User) = JsObject(Map(
    "id"           -> JsString(value.id),
    "email"        -> JsString(value.email),
    "createdAt"    -> JsString(value.createdAt.toString)
  ))
}

//----
implicit object VisitorWriter extends JsWriter[Visitor] {
  def write(value: Visitor) = value match {
    case anon: Anonymous => JsUtil.toJson(anon)
    case user: User      => JsUtil.toJson(user)
  }
}

val visitors: Seq[Visitor] = Seq(
  Anonymous("001", new Date),
  User("003", "dave@xample.com", new Date)
)

visitors.map(JsUtil.toJson(_))
visitors.map(JsUtil.toJson(_)).map(_.stringify)
