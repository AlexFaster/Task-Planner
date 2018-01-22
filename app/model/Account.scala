package model

case class Account(id: Long = 0, login: String, password: String) {
  def this(login: String, password: String) = this(0, login, password)
}
