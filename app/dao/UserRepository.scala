package dao

import com.google.inject.ImplementedBy
import dao.postgres.UserRepositoryImpl
import model.User

import scala.concurrent.Future

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository {

  def getUsers(): Future[Seq[User]]

  def getUser(id: Long): Future[Option[User]]
}