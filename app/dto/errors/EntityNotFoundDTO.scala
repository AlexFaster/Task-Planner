package dto.errors

import play.api.libs.json.Json

case class EntityNotFoundDTO(statusCode: Int, message: String)

object EntityNotFoundDTO {
  implicit val writes = Json.format[EntityNotFoundDTO]
}


