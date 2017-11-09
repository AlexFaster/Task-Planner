package model

case class Task(var id: Long = 0, title: String) {

  def patch(title: Option[String]): Task =
    this.copy(title = title.getOrElse(this.title))
}
