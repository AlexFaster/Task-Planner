package controllers

import javax.inject.{Inject, Singleton}

import dto.LoginDTO._
import dto.RegisterDTO._
import play.api.mvc.{AbstractController, ControllerComponents}
import service.AuthService

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthController @Inject()(
                                implicit ec: ExecutionContext,
                                cc: ControllerComponents,
                                authService: AuthService
                              ) extends AbstractController(cc) {

  def login() = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      errorForm => None,
      loginInfo => {
      }
    )
    Ok("login").withSession("userid" -> "1")
  }

  def register = Action.async( implicit request => {
    registerForm.bindFromRequest.fold(
      errorForm => Future(Ok),
      registerInfo => {
        authService.register(registerInfo)
        Future(Ok)
      }
    )
  })
}
