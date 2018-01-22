package controllers

import javax.inject._

import dto.UserDTO
import io.swagger.annotations.Api
import play.api.libs.json.Json
import play.api.mvc._
import service.UserService

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
@Api(value = "dev")
class UserController @Inject()(
                                userService: UserService,
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  def index = Action(implicit request =>
    Ok("dummy")
  )

  def getUsers = Action.async ( implicit request =>
    userService.getUsers().map(
      users => Ok(Json.toJson(
        users.map(user => UserDTO.assembleDTO(user))
      ))
    )
  )
}
