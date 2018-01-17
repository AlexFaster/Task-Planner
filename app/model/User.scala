package model

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}

class User(val id: Long, val name: String, val age: Int, val accountId: Long) extends Subject {

  override def roles: List[Role] = List(SecurityRole("user"))

  override def permissions: List[Permission] = List()

  override def identifier: String = name

}

object User {

  def apply(id: Long, name: String, age: Int, accountId: Long) = new User(id, name, age, accountId)

  def unapply(user: User): Option[(Long, String, Int, Long)] = Some(user.id, user.name, user.age, user.accountId)

  def tupled = (User.apply _).tupled
}
