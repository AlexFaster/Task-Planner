package dao.postgres

import javax.inject.{Inject, Singleton}

import dao.UserRepository
import model.User
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

@Singleton
class UserRepositoryImpl @Inject()(
                                    protected val dbConfigProvider: DatabaseConfigProvider
                                  ) extends UserRepository {

  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  import dbConfig.profile.api._

  private val Users = TableQuery[UserTable]


  def getUsers(): Future[Seq[User]] = {
    db.run(Users.to[Seq].result)
  }

  def getUser(id: Long): Future[Option[User]] = {
    db.run(Users.filter(_.id === id).result.headOption)
  }


  class UserTable(tag: Tag) extends Table[User](tag, "User") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def age = column[Int]("age")

    def accountId = column[Long]("accountId")

    def * = (id, name, age, accountId) <> (User.tupled, User.unapply)

    def account = foreignKey("ACCOUNT", accountId, new AccountRepositoryImpl(dbConfigProvider).Accounts)(_.id)
  }

}

//object UserRepositoryImpl {
//  private val users = Seq[User](
//    new User(1, "aa", 3, 1),
//    new User(2, "bb", 5, 2)
//  )
//}
