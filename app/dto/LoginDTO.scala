package dto

import io.swagger.annotations.ApiModelProperty
import play.api.data.Form
import play.api.data.Forms._

case class LoginDTO(
                     @ApiModelProperty(dataType = "String", required = false) login: String,
                     @ApiModelProperty(dataType = "String", required = false) password: String
)

object LoginDTO {
  val loginForm = Form(
    mapping(
      "login" -> text,
      "password" -> text
    )(LoginDTO.apply)(LoginDTO.unapply)
  )
}
