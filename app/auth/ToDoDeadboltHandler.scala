package auth

import java.util.Base64
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
    request.headers.get("token") match {
      case Some(token) =>
        // get from database, identity platform, cache, etc, if some
        // identifier is present in the request
        val tokens = new String(Base64.getDecoder.decode(token)).split(":")
        userRepository.getUserByToken(tokens(0).toLong, tokens(1)).map(user => {
          if (user.nonEmpty) {
            print(user.get.id)
            user
          } else {
            None
          }
        })
      case _ => Future(None)
    }


  override def onAuthFailure[A](request: AuthenticatedRequest[A]): Future[Result] = {
    Future(
      Results.Unauthorized
    )
  }
}