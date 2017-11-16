package service

import javax.inject.Inject

import dao.TaskRepository
import dto.TaskDTOIn
import exceptions.EntityNotFoundException
import model.Task

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

  def updateTask(id: Long, task: TaskDTOIn): Future[Task] = {
    taskDAO.update(id, task).map {
      case Some(value) => value
      case None => throw EntityNotFoundException()
    }
  }

  def addTask(task: Task): Future[Task] = {
    taskDAO.add(task)
  }

  def deleteTask(id: Long): Future[Int] = {
    taskDAO.delete(id)
  }

}
