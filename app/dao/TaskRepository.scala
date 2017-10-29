package dao

import javax.inject.Singleton

import dto.Task

@Singleton
class TaskRepository {

  def getTasks: Seq[Task] = {
    TaskRepository.tasks
  }

}

object TaskRepository {
  val tasks = Seq(
    Task(1, "Title1"),
    Task(2, "Title2")
  )

}
