package service

import javax.inject.Inject

import dao.TaskRepository
import exceptions.EntityNotFoundException
import model.Task

class TaskService @Inject()(taskDAO: TaskRepository) {

  def getTasks: Seq[Task] = {
    taskDAO.getAll
  }

  def getTask(id: Long): Task = {
    taskDAO.getById(id) match {
      case Some(value) => value
      case None => throw EntityNotFoundException()
    }
  }

  def addTask(task: Task): Task = {
    taskDAO.add(task)
  }

}
