package controllers

import javax.inject.{Inject, Singleton}

import dto.{TaskDTO, TaskDTOIn}
import io.swagger.annotations._
import model.Task
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import service.TaskService
import util.HttpStatus

import scala.concurrent.ExecutionContext

@Singleton
@Api(value = "dev")
class TaskController @Inject()(
                                implicit ec: ExecutionContext,
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
  def getTasks = Action.async { _ =>
    taskService.getTasks.map(
      tasks => Ok(
        Json.toJson(
          tasks.map(task =>
            TaskDTO.assembleDTO(task)
          )
        )
      )
    )
  }

  @ApiOperation(
    value = "Retrieve task",
    response = classOf[TaskDTOIn]
  )
  @ApiResponses(
    Array(
      new ApiResponse(code = HttpStatus.OK_200, message = "Retrieve task")
    )
  )
  def getTask(id: Long) = Action.async { _ =>
    taskService.getTask(id).map(
      task => Ok(Json.toJson(TaskDTO.assembleDTO(task)))
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
      dataType = "dto.TaskDTOIn",
      paramType = "body",
      name = "body",
      required = true,
      allowMultiple = false,
      value = "The task to create"))
  )
  def addTask = Action.async { request =>
    val task = request.body.asJson.get.as[TaskDTOIn]
    taskService.addTask(Task(title = task.title.get)).map(
      task => Ok(Json.toJson(TaskDTO.assembleDTO(task)))
    )
  }
}
