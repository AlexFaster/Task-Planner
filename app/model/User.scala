package model

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}

class User(val id: Long = 0, val name: Option[String], val age: Option[Int], val accountId: Long) extends Subject {

  def this(name: String, age: Int, accountId: Long) = this(name: Some(name), Some(age), accountId)

  def this(accountId: Long) = this(None, None, None, accountId)

  override def roles: List[Role] = List(SecurityRole("user"))

  override def permissions: List[Permission] = List()

  override def identifier: String = "user"

}

object User {

  def apply(id: Long, name: Option[String], age: Option[Int], accountId: Long) =
    new User(id, name, age, accountId)

  def unapply(user: User) = Some(user.id, user.name, user.age, user.accountId)

  def tupled = (User.apply _).tupled
}
