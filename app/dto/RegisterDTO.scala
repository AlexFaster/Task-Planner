package dto

import io.swagger.annotations.ApiModelProperty
import play.api.data.Forms.mapping
import play.api.data.Form
import play.api.data.Forms._

case class RegisterDTO(
                        @ApiModelProperty(dataType = "String", required = false) login: String,
                        @ApiModelProperty(dataType = "String", required = false) password: String
                      )

object RegisterDTO {
  val registerForm = Form(
    mapping(
      "login" -> text,
      "password" -> text
    )(RegisterDTO.apply)(RegisterDTO.unapply)
  )
}