package controllers

import javax.inject._

import dto.User
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class UserController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action(implicit request =>
    Ok("dummy")
  )

  def getUsers() = Action(implicit request =>
    Ok(
      Json.toJson(
        Seq(
          User(1, "aa", 3),
          User(2, "bb", 5)
        )
      )
    )
  )
}
