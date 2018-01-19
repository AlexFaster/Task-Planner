package service

import javax.inject.Inject

import dao.{AccountRepository, UserRepository}
import dto.RegisterDTO
import model.{Account, User}

class AuthService @Inject()(
                             userDAO: UserRepository,
                             accountDAO: AccountRepository
                           ) {

  def register(registerDTO: RegisterDTO) {
    val account = new Account(registerDTO.login, registerDTO.password)
    val user = for {
      accountFuture <- accountDAO.insertAccount(account)
    } yield new User(accountFuture.id.get)
    userDAO.insertUser(user)
  }
}
