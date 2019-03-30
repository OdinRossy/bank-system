package com.odinrossy.banksystem.services.user

import com.odinrossy.banksystem.exceptions.user.UserNotFoundException
import com.odinrossy.banksystem.exceptions.user.WrongUserPasswordException
import com.odinrossy.banksystem.models.user.User
import org.springframework.stereotype.Service

@Service
interface UserService {

    List<User> findAll()

    User findByIdPassport(String idPassport) throws UserNotFoundException

    User findByIdPassportAndPassword (String idPassport, String password) throws UserNotFoundException, WrongUserPasswordException

    User save(User user)

    User update(String idPassport, User user)

    void delete(String idPassport)

    void checkAuthorization()

}
