package model

import dto.TaskDTOIn
import model.TaskStatus.TaskStatus

case class Task(var id: Long = 0, title: String, description: String, status: TaskStatus = TaskStatus.INCOMPLETE) {

  def patch(task: TaskDTOIn): Task =
    this.copy(
      title = task.title.getOrElse(this.title),
      description = task.description.getOrElse(this.description),
      status = task.status.getOrElse(this.status)
    )
}
