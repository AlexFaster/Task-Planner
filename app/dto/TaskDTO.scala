package dto

import model.Task
import model.TaskStatus.TaskStatus
import play.api.libs.json._

case class TaskDTO(
                    id: Long,
                    title: String,
                    description: String,
                    status: TaskStatus
                  )

object TaskDTO {
  implicit val taskWrites = Json.format[TaskDTO]

  def assembleDTO(task: Task): TaskDTO = {
    TaskDTO(task.id, task.title, task.description, task.status)
  }
}