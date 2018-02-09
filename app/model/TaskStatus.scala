package model

import play.api.libs.json.Reads

object TaskStatus extends Enumeration {
  type TaskStatus = Value

  val INCOMPLETE = Value("INCOMPLETE")
  val COMPLETED = Value("COMPLETED")

  implicit val statusReads = Reads.enumNameReads(TaskStatus)
}


