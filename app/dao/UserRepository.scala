package dao

import javax.inject.Singleton

import dto.UserDTO

@Singleton
class UserRepository {

  def getUsers() = {
    UserRepository.users
  }
}

object UserRepository {
  private val users = Seq(
    UserDTO(1, "aa", 3),
    UserDTO(2, "bb", 5)
  )
}