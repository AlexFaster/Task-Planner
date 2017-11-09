package dto

import io.swagger.annotations.ApiModelProperty
import model.Task
import play.api.libs.json._

case class TaskDTOIn(
                      @ApiModelProperty(dataType = "Long", required = false) id: Option[Long],
                      @ApiModelProperty(dataType = "String", required = false) title: Option[String]
                    )

object TaskDTOIn {
  implicit val taskWrites = Json.format[TaskDTOIn]

  def assembleDTO(task: Task): TaskDTOIn = {
    TaskDTOIn(Option(task.id), Option(task.title))
  }
}