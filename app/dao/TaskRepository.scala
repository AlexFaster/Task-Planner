package dao

import com.google.inject.ImplementedBy
import dao.postgres.TaskRepositoryImpl
import model.Task

import scala.collection.mutable.ArrayBuffer

@ImplementedBy(classOf[TaskRepositoryImpl])
trait TaskRepository {

  def getAll: Seq[Task]

  def add(task: Task): Task

  def getById(id: Long): Option[Task]

  def update(task: Task): Task

  def delete(task: Task): Boolean

  def delete(id: Long): Boolean

}

object TaskRepository {
  val tasks = ArrayBuffer(
    Task(1, "Title1"),
    Task(2, "Title2")
  )

}
