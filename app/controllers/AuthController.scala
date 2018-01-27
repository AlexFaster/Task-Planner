package controllers

import javax.inject.{Inject, Singleton}

import dto.UserDTO
import io.swagger.annotations._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import service.AuthService
import util.HttpStatus

import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "auth")
class AuthController @Inject()(
                                implicit ec: ExecutionContext,
                                cc: ControllerComponents,
                                authService: AuthService
                              ) extends AbstractController(cc) {

  @ApiOperation(
    value = "login",
    response = classOf[Void]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Logged in")
    )
  )
  def login(login: String, password: String) = Action.async(implicit request => {
    val userFut = authService.login(login, password)
    userFut.map {
      case Some(user) => Ok.withSession("userid" -> user.id.toString)
      case None => Forbidden
    }
  })

  @ApiOperation(
    value = "register",
    response = classOf[UserDTO]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Registered")
    )
  )
  def register(login: String, password: String) = Action.async(implicit request => {
    authService.register(login, password).map(
      user => Ok(Json.toJson(UserDTO.assembleDTO(user)))
    )
  })
}
