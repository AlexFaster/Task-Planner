package dao.postgres

import javax.inject.{Inject, Singleton}

import dao.TaskRepository
import model.Task
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class TaskRepositoryImpl @Inject()(
                                    protected val dbConfigProvider: DatabaseConfigProvider
                                  ) extends TaskRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.profile.api._

  private val Tasks = TableQuery[TaskTable]

  override def getAll: Future[Seq[Task]] = {
    db.run(Tasks.to[Seq].result)
  }

  override def add(task: Task) = {
    db.run(Tasks returning Tasks.map(_.id) += task).map(id => {
      task.id = id
      task
    })
  }

  override def getById(id: Long) = {
    db.run(Tasks.filter(_.id === id).result.headOption)
  }

  override def update(task: Task) = {
    val query = Tasks.filter(_.id === task.id)

    val updateQuery = query.result.head.flatMap {task =>
      query.update(task.patch(Option(task.title)))
    }

    db.run(updateQuery).map(
      _ => task
    )
  }

  override def delete(task: Task) = {
    delete(task.id)
  }

  override def delete(id: Long) = {
    db.run(Tasks.filter(_.id === id).delete)
  }

  private class TaskTable(tag: Tag) extends Table[Task](tag, "Task") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def * = (id, name) <> (Task.tupled, Task.unapply)
  }

}
