package dao

import dto.{Task}

class TaskDAO {

  def getAllTasks: Seq[Task] = {
    TaskDAO.tasks
  }

}

object TaskDAO {
  val tasks = Seq(
    Task(1, "Title1"),
    Task(2, "Title2")
  )

}
