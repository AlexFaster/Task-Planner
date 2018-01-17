package dao.postgres

import javax.inject.Inject

import dao.AccountRepository
import model.{Account, User}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

class AccountRepositoryImpl @Inject() (
                                        protected val dbConfigProvider: DatabaseConfigProvider
                                      ) extends AccountRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.profile.api._

  private[postgres] val Accounts = TableQuery[AccountTable]

  class AccountTable(tag: Tag) extends Table[Account](tag, "Account") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def login = column[String]("login")

    def password = column[String]("password")

    def * = (id, login, password) <> (Account.tupled, Account.unapply)

  }

}

object AccountRepositoryImpl {



}
