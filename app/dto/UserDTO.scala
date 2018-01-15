package dto

import play.api.libs.json.Json

case class UserDTO(id: Long, name:String, age:Int)

object UserDTO {
  implicit val userReads = Json.format[UserDTO]
}