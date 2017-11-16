package model

import play.api.libs.json.Reads

object TaskStatus extends Enumeration {
  type TaskStatus = Value

  val INCOMPLETE = Value("INCOMPLETE")
  val COMPLETE = Value("COMPLETE")

  implicit val statusReads = Reads.enumNameReads(TaskStatus)
}


