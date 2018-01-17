package dto

import model.User
import play.api.libs.json.Json

case class UserDTO(id: Long, name:String, age:Int)

object UserDTO {
  implicit val userReads = Json.format[UserDTO]

  def assembleDTO(user: User): UserDTO = {
    UserDTO(user.id, user.name, user.age)
  }
}