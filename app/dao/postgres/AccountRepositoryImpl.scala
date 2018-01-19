package dao.postgres

import javax.inject.Inject

import dao.AccountRepository
import model.Account
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

class AccountRepositoryImpl @Inject()(
                                       protected val dbConfigProvider: DatabaseConfigProvider
                                     ) extends AccountRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.profile.api._

  private[postgres] val Accounts = TableQuery[AccountTable]

  def insertAccount(account: Account): Future[Account] = {
    db.run(
      Accounts.map(a => (a.login, a.password))
        returning Accounts.map(a => a.id)
        into ((entity, id) => Account(id, entity._1, entity._2)) += (account.login, account.password)
    )
  }

  class AccountTable(tag: Tag) extends Table[Account](tag, "Account") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (id, login, password) <> (Account.tupled, Account.unapply)

  }

}

