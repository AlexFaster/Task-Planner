package dto

import model.Task
import play.api.libs.json._

case class TaskDTO(id: Option[Long], title: String)

object TaskDTO {
  implicit val taskWrites = Json.format[TaskDTO]

  def assembleDTO(task: Task): TaskDTO = {
    TaskDTO(Option(task.id), task.title)
  }
}