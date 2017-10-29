package controllers

import javax.inject.{Inject, Singleton}

import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class AuthController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def login() = Action { request =>
    Ok("login")
  }
}
