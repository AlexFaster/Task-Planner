package model

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}

class User(val id: Long = 0, val name: Option[String], val age: Option[Int], val accountId: Long, var token: Option[String]) extends Subject {

  def this(name: String, age: Int, accountId: Long, token: Option[String]) = this(name = Some(name), age = Some(age), accountId = accountId, token = token)

  def this(accountId: Long) = this(name = None, age = None, accountId = accountId, token = None)

  override def roles: List[Role] = List(SecurityRole("user"))

  override def permissions: List[Permission] = List()

  override def identifier: String = id.toString

}

object User {

  def apply(id: Long, name: Option[String], age: Option[Int], accountId: Long, token: Option[String]) =
    new User(id, name, age, accountId, token)

  def unapply(user: User) = Some(user.id, user.name, user.age, user.accountId, user.token)

  def tupled = (User.apply _).tupled
}
