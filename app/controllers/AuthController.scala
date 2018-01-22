package controllers

import javax.inject.{Inject, Singleton}

import dto.LoginDTO._
import dto.RegisterDTO._
import dto.UserDTO
import io.swagger.annotations._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import service.AuthService
import util.HttpStatus

import scala.concurrent.{ExecutionContext, Future}

@Singleton
@Api(value = "dev")
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
  @ApiImplicitParams(
    Array(new ApiImplicitParam(
      dataType = "dto.LoginDTO",
      paramType = "body",
      name = "body",
      required = true,
      allowMultiple = false,
      value = "session with user id"))
  )
  def login() = Action.async(implicit request => {
    loginForm.bindFromRequest.fold(
      errorForm => Future(BadRequest),
      loginInfo => {
        val userFut = authService.login(loginInfo.login, loginInfo.password)
        userFut.map {
          case Some(user) => Ok.withSession("userid" -> user.id.toString)
          case None => Forbidden
        }
      }
    )
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
  @ApiImplicitParams(
    Array(new ApiImplicitParam(
      dataType = "dto.RegisterDTO",
      paramType = "body",
      name = "body",
      required = true,
      allowMultiple = false,
      value = "registered user id"))
  )
  def register() = Action.async(implicit request => {
    registerForm.bindFromRequest.fold(
      errorForm => Future(BadRequest),
      registerInfo => {
        authService.register(registerInfo.login, registerInfo.password).map(
          user => Ok(Json.toJson(UserDTO.assembleDTO(user)))
        )
      }
    )
  })
}
