package auth

import be.objectify.deadbolt.scala.models.Subject
import be.objectify.deadbolt.scala.{AuthenticatedRequest, DeadboltHandler, DynamicResourceHandler}
import model.User
import play.api.mvc.{Request, Result, Results}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ToDoDeadboltHandler extends DeadboltHandler {

  override def beforeAuthCheck[A](request: Request[A]): Future[Option[Result]] = Future {
    None
  }

  override def getDynamicResourceHandler[A](request: Request[A]): Future[Option[DynamicResourceHandler]] = Future {
    None
  }

  override def getSubject[A](request: AuthenticatedRequest[A]): Future[Option[Subject]] =
    Future {
      request.subject.orElse {
        // replace request.session.get("userId") with how you identify the user
        request.session.get("userId") match {
          case Some(userId) =>
            // get from database, identity platform, cache, etc, if some
            // identifier is present in the request
            Option(new User(1, "aa", 4))
          case _ => Option(new User(1, "aa", 4))
        }
      }
    }

  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future (
      Results.Forbidden("403")
    )
  }
}