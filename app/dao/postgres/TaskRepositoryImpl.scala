package dao.postgres

import javax.inject.Singleton

import dao.TaskRepository
import dto.Task

@Singleton
class TaskRepositoryImpl extends TaskRepository {

  override def getAll: Seq[Task] = {
    TaskRepository.tasks
  }

  override def add(task: Task) = {
    task.id = getAll.size + 1
    task
  }

  override def getById(id: Long) = {
    TaskRepository.tasks.find(_.id == id)
  }

  override def update(task: Task) = {
    task
  }

  override def delete(task: Task) = {
    false
  }

  override def delete(id: Long) = {
    false
  }
}
