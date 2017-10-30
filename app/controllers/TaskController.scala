package controllers

import javax.inject.{Inject, Singleton}

import dto.Task
import io.swagger.annotations._
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import service.TaskService
import util.HttpStatus

@Singleton
@Api(value = "dev")
class TaskController @Inject()(
                                taskService: TaskService,
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
      Json.toJson(taskService.getTasks)
    )
  }

  @ApiOperation(
    value = "Retrieve task",
    response = classOf[Task]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Retrieve task")
    )
  )
  def getTask(id: Long) = Action { request =>
    Ok(
      Json.toJson(taskService.getTask(id))
    )
  }

  @ApiOperation(
    value = "Add task",
    response = classOf[Task]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Created task")
    )
  )
  @ApiImplicitParams(
    Array(new ApiImplicitParam(
      dataType = "dto.Task",
      paramType = "body",
      name = "body",
      required = true,
      allowMultiple = false,
      value = "The task to create"))
  )
  def addTask = Action { request =>
    val task = request.body.asJson.get.as[Task]
    Ok(
      Json.toJson(taskService.addTask(task))
    )
  }
}
