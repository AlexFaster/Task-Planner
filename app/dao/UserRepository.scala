package dao

import javax.inject.Singleton

import dto.User

@Singleton
class UserRepository {

  def getUsers() = {
    UserRepository.users
  }
}

object UserRepository {
  private val users = Seq(
    User(1, "aa", 3),
    User(2, "bb", 5)
  )
}