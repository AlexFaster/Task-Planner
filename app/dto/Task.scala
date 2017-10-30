package dto

import play.api.libs.json.Json

case class Task(var id: Long, title: String)

object Task {
  implicit val taskWrites = Json.format[Task]
}