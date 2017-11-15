package model

import dto.TaskDTOIn

case class Task(var id: Long = 0, title: String) {

  def patch(task: TaskDTOIn): Task =
    this.copy(title = task.title.getOrElse(this.title))
}
