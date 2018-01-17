package auth

import javax.inject.{Inject, Singleton}

import be.objectify.deadbolt.scala.{DeadboltHandler, HandlerKey}
import be.objectify.deadbolt.scala.cache.HandlerCache
import dao.UserRepository

/**
  * @author Steve Chaloner (steve@objectify.be)
  */
@Singleton
class MyHandlerCache @Inject()(implicit userRepository: UserRepository) extends HandlerCache {

  val defaultHandler: DeadboltHandler = new ToDoDeadboltHandler

  val handlers: Map[Any, DeadboltHandler] = Map(HandlerKeys.defaultHandler -> defaultHandler,
    HandlerKeys.userlessHandler -> new MyUserlessDeadboltHandler)

  override def apply(): DeadboltHandler = defaultHandler

  override def apply(handlerKey: HandlerKey): DeadboltHandler = handlers(handlerKey)
}