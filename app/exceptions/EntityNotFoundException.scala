package exceptions

case class EntityNotFoundException(message: String = "Not Found") extends RuntimeException(message)


