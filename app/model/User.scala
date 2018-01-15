package model

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}

class User(id: Long, name:String, age:Int) extends Subject {
  override def roles: List[Role] = List(SecurityRole("user"))

  override def permissions: List[Permission] = List()

  override def identifier: String = name
}
