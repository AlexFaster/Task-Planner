package controllers

import javax.inject._

import dao.UserRepository
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class UserController @Inject()(
                                userDAO: UserRepository,
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  def index = Action(implicit request =>
    Ok("dummy")
  )

  def getUsers = Action(implicit request =>
    Ok(
      Json.toJson(
        userDAO.getUsers()
      )
    )
  )
}
