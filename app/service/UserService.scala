package service

import javax.inject.{Inject, Singleton}

import dao.UserRepository
import exceptions.EntityNotFoundException
import model.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class UserService @Inject() (userRepository: UserRepository) {

  def getUserById(id:Long): Future[User] = {
    userRepository.getUser(id).map {
      case Some(user) => user
      case None => throw EntityNotFoundException()
    }
  }

  def getUsers(): Future[Seq[User]] = {
    userRepository.getUsers()
  }
}
