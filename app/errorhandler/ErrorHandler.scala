package errorhandler

import javax.inject.Singleton

import dto.errordto.EntityNotFoundDTO
import exceptions.EntityNotFoundException
import play.api.http.HttpErrorHandler
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._
import util.HttpStatus

import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Future.successful(
      Status(statusCode)("A client error occurred: " + message)
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      exception match {
        case e: EntityNotFoundException => NotFound(Json.toJson(EntityNotFoundDTO(HttpStatus.NOT_FOUND_404, e.getMessage)))
        case t: Throwable => throw t
      }
    )
  }
}