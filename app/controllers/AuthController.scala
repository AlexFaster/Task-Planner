package controllers

import javax.inject.{Inject, Singleton}

import dto.LoginDTO._
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

@Singleton
class AuthController @Inject()(
                                implicit ec: ExecutionContext,
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  def login() = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      errorForm => None,
      loginInfo => {
        loginInfo.login
        loginInfo.password
      }
    )
    Ok("login").withSession("userid" -> "1")
  }
}
