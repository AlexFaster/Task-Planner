package dao.postgres

import java.util.concurrent.TimeUnit
import javax.inject.{Inject, Singleton}

import dao.TaskRepository
import model.Task
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class TaskRepositoryImpl @Inject()(
                                    protected val dbConfigProvider: DatabaseConfigProvider
                                  ) extends TaskRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.profile.api._

  private val Tasks = TableQuery[TaskTable]

  override def getAll: Seq[Task] = {
    Await.result(db.run(Tasks.to[List].result), Duration.create(5L, TimeUnit.SECONDS))
  }

  override def add(task: Task) = {
    task.id = getAll.size + 1
    TaskRepository.tasks += task
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

  private class TaskTable(tag: Tag) extends Table[Task](tag, "Task") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <> (Task.tupled, Task.unapply)
  }

}
