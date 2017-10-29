package dto

import play.api.libs.json.Json

case class User(id: Long, name:String, age:Int)

object User {
  implicit val userReads = Json.format[User]
}