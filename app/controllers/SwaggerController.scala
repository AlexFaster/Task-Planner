package controllers

import javax.inject.Inject

import play.api.mvc.{AbstractController, ControllerComponents}

class SwaggerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def redirectDocs = Action {
    Redirect(url = "/assets/lib/swagger-ui/index.html", queryString = Map("url" -> Seq("/swagger.json")))
  }

}
