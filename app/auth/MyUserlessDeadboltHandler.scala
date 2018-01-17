package auth

import javax.inject.Inject

import be.objectify.deadbolt.scala.AuthenticatedRequest
import be.objectify.deadbolt.scala.models.Subject
import dao.UserRepository

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MyUserlessDeadboltHandler @Inject()(implicit userRepository: UserRepository) extends ToDoDeadboltHandler {
  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] = Future(None)
}
