package dto

import play.api.libs.json.Json

case class TokenDTO(token: String)

object TokenDTO {

  implicit val tokenReads = Json.format[TokenDTO]

  def assembleDTO(token: String): TokenDTO = {
    TokenDTO(token)
  }
}
