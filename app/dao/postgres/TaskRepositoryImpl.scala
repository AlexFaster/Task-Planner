package dao.postgres

import javax.inject.{Inject, Singleton}

import dao.TaskRepository
import dto.TaskDTOIn
import model.Task
import play.api.db.slick.DatabaseConfigProvider
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

  override def update(taskDTOIn: TaskDTOIn) = {

//    val query =

//    query.update(query.result.headOption.map(item => item.map(task => task.patch(taskDTOIn))))

    for {
      existing <- Tasks.filter(_.id === taskDTOIn.id).result.headOption
      merged <- existing.patch(taskDTOIn)
     } yield merged

//    db.run(updateQuery).flatMap {
//      x => x match {
//        case 1 => getById(taskDTOIn.id.get)
//        case _ => Future(Option.empty)
//      }
//    }


  }


  override def delete(id: Long) = {
    db.run(Tasks.filter(_.id === id).delete)
  }

  private class TaskTable(tag: Tag) extends Table[Task](tag, "Task") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("title")

    def * = (id, name) <> (Task.tupled, Task.unapply)
  }

}
