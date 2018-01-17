package model

case class Account(id: Long, login: String, password: String)

object Account {
  val accounts = Seq[Account](
    Account(1, "alex", "123"),
    Account(2, "zhenya", "321")
  )
}
