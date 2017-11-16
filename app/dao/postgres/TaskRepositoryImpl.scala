package dao.postgres

import javax.inject.{Inject, Singleton}

import dao.TaskRepository
import dto.TaskDTOIn
import model.TaskStatus.TaskStatus
import model.{Task, TaskStatus}
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio.DBIOAction
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

  override def update(id: Long, taskDTOIn: TaskDTOIn) = {
    val query = Tasks.filter(_.id === id)
    val update = query.result.headOption flatMap {
      case Some(v) => query.update(v.patch(taskDTOIn))
      case None => DBIOAction.successful(0)
    }
    db.run(update).flatMap(_ => getById(id))
  }


  override def delete(id: Long) = {
    db.run(Tasks.filter(_.id === id).delete)
  }

  private class TaskTable(tag: Tag) extends Table[Task](tag, "Task") {

    implicit val myEnumMapper = MappedColumnType.base[TaskStatus, String](
      e => e.toString,
      s => TaskStatus.withName(s)
    )

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("title")

    def description = column[String]("description")

    def status = column[TaskStatus]("status")

    def * = (id, name, description, status) <> (Task.tupled, Task.unapply)
  }

}
