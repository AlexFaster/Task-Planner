package controllers

import java.util.{Base64, UUID}
import javax.inject.{Inject, Singleton}

import dao.UserRepository
import dto.LoginDTO._
import dto.RegisterDTO._
import dto.{TokenDTO, UserDTO}
import io.swagger.annotations._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import service.AuthService
import util.HttpStatus

import scala.concurrent.{ExecutionContext, Future}

@Singleton
@Api(
  value = "auth",
  produces = "application/json"
)
class AuthController @Inject()(
                                implicit ec: ExecutionContext,
                                cc: ControllerComponents,
                                authService: AuthService,
                                userRepository: UserRepository
                              ) extends AbstractController(cc) {

  @ApiOperation(
    value = "login",
    response = classOf[Void],
    consumes = "application/x-www-form-urlencoded"
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Logged in")
    )
  )
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(name = "login", required = true, dataType = "string", paramType = "formData"),
      new ApiImplicitParam(name = "password", required = true, dataType = "string", paramType = "formData")
    )
  )
  def login() = Action.async(implicit request => {
    loginForm.bindFromRequest.fold(
      errorForm => {
        print("error form")
        Future(BadRequest)
      },
      loginInfo => {
        val userFut = authService.login(loginInfo.login, loginInfo.password)
        userFut.map {
          case Some(user) =>
            val token = UUID.randomUUID.toString
            val tokenWithId = new String(Base64.getEncoder.encode((user.id + ":" + token).getBytes))
            user.token = Option(token)
            userRepository.update(user.id, user)
            Ok(
              Json.toJson(
                TokenDTO.assembleDTO(tokenWithId)
              )
            )
          case None => Forbidden
        }
      }
    )
  })

  @ApiOperation(
    value = "register",
    response = classOf[UserDTO],
    consumes = "application/x-www-form-urlencoded"
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Registered")
    )
  )
  @ApiImplicitParams(
    Array(
      new ApiImplicitParam(name = "login", required = true, dataType = "string", value = "Login", paramType = "formData"),
      new ApiImplicitParam(name = "password", required = true, dataType = "string", value = "Password", paramType = "formData")
    )
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
