package service

import javax.inject.Inject

import dao.TaskRepository
import exceptions.EntityNotFoundException
import model.Task

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TaskService @Inject()(taskDAO: TaskRepository) {

  def getTasks: Future[Seq[Task]] = {
    taskDAO.getAll
  }

  def getTask(id: Long): Future[Task] = {
    taskDAO.getById(id).map {
      case Some(value) => value
      case None => throw EntityNotFoundException()
    }
  }

  def addTask(task: Task): Future[Task] = {
    taskDAO.add(task)
  }

}
