package dto

import io.swagger.annotations.ApiModelProperty
import model.Task
import play.api.libs.json._

case class TaskDTO(
                    @ApiModelProperty(dataType = "Long", required = true) id: Long,
                    @ApiModelProperty(dataType = "String", required = true) title: String
                  )

object TaskDTO {
  implicit val taskWrites = Json.format[TaskDTO]

  def assembleDTO(task: Task): TaskDTO = {
    TaskDTO(task.id, task.title)
  }
}