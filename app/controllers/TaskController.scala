package controllers

import javax.inject.{Inject, Singleton}

import dao.TaskRepository
import dto.Task
import io.swagger.annotations.{Api, ApiOperation, ApiResponse, ApiResponses}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import util.HttpStatus

@Singleton
@Api(value = "dev")
class TaskController @Inject()(
                                taskDAO: TaskRepository,
                                cc: ControllerComponents
                              ) extends AbstractController(cc) {

  @ApiOperation(
    value = "Retrieve tasks",
    response = classOf[Task],
    responseContainer = "List"
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Retrieve tasks")
    )
  )
  def getTasks = Action { request =>
    Ok(
      Json.toJson(taskDAO.getTasks)
    )
  }
}
