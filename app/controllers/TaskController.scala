package controllers

import javax.inject.Inject

import dao.TaskRepository
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}

class TaskController @Inject()(
                                taskDAO: TaskRepository,
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  def getTasks = Action { request =>
    Ok(
      Json.toJson(taskDAO.getTasks)
    )
  }
}
