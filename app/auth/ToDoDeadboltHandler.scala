package auth

import javax.inject.Inject

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import dao.UserRepository
import play.api.mvc.{Request, Result, Results}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ToDoDeadboltHandler @Inject()(implicit userRepository: UserRepository) extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future {
    None
  }

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = Future {
    None
  }

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] =
    request.session.get("userid") match {
      case Some(userId) =>
        // get from database, identity platform, cache, etc, if some
        // identifier is present in the request
        userRepository.getUser(userId.toInt).map(user => {
          print(user.get.name)
          user
        })
      case _ => Future(None)
    }


  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future(
      Results.Forbidden("403")
    )
  }
}