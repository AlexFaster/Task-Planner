package dao

import com.google.inject.ImplementedBy
import dao.postgres.TaskRepositoryImpl
import model.Task

import scala.concurrent.Future

@ImplementedBy(classOf[TaskRepositoryImpl])
trait TaskRepository {

  def getAll: Future[Seq[Task]]

  def add(task: Task): Future[Task]

  def getById(id: Long): Future[Option[Task]]

  def update(task: Task): Future[Task]

  def delete(task: Task): Unit

  def delete(id: Long): Unit

}

