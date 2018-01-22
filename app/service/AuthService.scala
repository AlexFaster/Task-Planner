package service

import javax.inject.Inject

import dao.{AccountRepository, UserRepository}
import model.{Account, User}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AuthService @Inject()(
                             userDAO: UserRepository,
                             accountDAO: AccountRepository
                           ) {

  def register(login: String, password: String): Future[User] = {
    val account = new Account(login, password)
    accountDAO.insertAccount(account).flatMap(account => {
      val user = new User(account.id)
      userDAO.insertUser(user)
    })
  }

  def login(login: String, password: String): Future[Option[User]] = {
    accountDAO.login(login, password).flatMap {
      case Some(account) => userDAO.getUserByAccountId(account.id)
      case None => Future(None)
    }
  }
}
