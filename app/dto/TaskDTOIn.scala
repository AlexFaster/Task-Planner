package dto

import io.swagger.annotations.ApiModelProperty
import model.TaskStatus.TaskStatus
import play.api.libs.json._

case class TaskDTOIn(
                      @ApiModelProperty(dataType = "String", required = false) title: Option[String],
                      @ApiModelProperty(dataType = "String", required = false) description: Option[String],
                      @ApiModelProperty(dataType = "String", required = false, allowableValues = "COMPLETE, INCOMPLETE") status: Option[TaskStatus]
                    )

object TaskDTOIn {
  implicit val taskWrites = Json.format[TaskDTOIn]
}