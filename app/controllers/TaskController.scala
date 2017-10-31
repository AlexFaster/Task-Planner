package controllers

import javax.inject.{Inject, Singleton}

import dto.TaskDTO
import io.swagger.annotations._
import model.Task
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
    response = classOf[TaskDTO],
    responseContainer = "List"
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Retrieve tasks")
    )
  )
  def getTasks = Action { request =>
    Ok(
      Json.toJson(taskService.getTasks.map(task => TaskDTO.assembleDTO(task)))
    )
  }

  @ApiOperation(
    value = "Retrieve task",
    response = classOf[TaskDTO]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Retrieve task")
    )
  )
  def getTask(id: Long) = Action { request =>
    Ok(
      Json.toJson(TaskDTO.assembleDTO(taskService.getTask(id)))
    )
  }

  @ApiOperation(
    value = "Add task",
    response = classOf[TaskDTO]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Created task")
    )
  )
  @ApiImplicitParams(
    Array(new ApiImplicitParam(
      dataType = "dto.TaskDTO",
      paramType = "body",
      name = "body",
      required = true,
      allowMultiple = false,
      value = "The task to create"))
  )
  def addTask = Action { request =>
    val task = request.body.asJson.get.as[TaskDTO]
    Ok(
      Json.toJson(TaskDTO.assembleDTO(taskService.addTask(Task(title = task.title))))
    )
  }
}
