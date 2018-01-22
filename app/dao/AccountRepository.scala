package dao


import com.google.inject.ImplementedBy
import dao.postgres.AccountRepositoryImpl
import model.Account

import scala.concurrent.Future

@ImplementedBy(classOf[AccountRepositoryImpl])
trait AccountRepository {

  def insertAccount(account: Account): Future[Account]

  def login(login: String, password: String): Future[Option[Account]]
}
