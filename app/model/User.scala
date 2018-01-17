package model

import be.objectify.deadbolt.scala.models.{Permission, Role, Subject}

class User(val id: Long, val name:String, val age:Int, val accountId: Int) extends Subject {

  override def roles: List[Role] = List(SecurityRole("user"))

  override def permissions: List[Permission] = List()

  override def identifier: String = name

}
