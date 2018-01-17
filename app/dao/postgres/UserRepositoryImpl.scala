package dao.postgres

import javax.inject.Singleton

import dao.UserRepository
import model.User

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class UserRepositoryImpl extends UserRepository {

  def getUsers(): Future[Seq[User]] = {
    Future(UserRepositoryImpl.users)
  }

  def getUser(id: Long): Future[Option[User]] = {
    Future(UserRepositoryImpl.users.find(user => user.id == id))
  }
}

object UserRepositoryImpl {
  private val users = Seq[User](
    new User(1, "aa", 3, 1),
    new User(2, "bb", 5, 2)
  )
}
