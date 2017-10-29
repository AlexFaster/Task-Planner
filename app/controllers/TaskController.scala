package controllers

import javax.inject.Inject

import dao.TaskDAO
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

class TaskController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getTasks = Action { request =>
    Ok(
      Json.toJson(new TaskDAO().getAllTasks)
    )
  }
}
