package com.odinrossy.banksystem.repositories.user

import com.odinrossy.banksystem.models.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends CrudRepository<User, Long> {

    User findByIdPassport(String idPassport)

    User findByIdPassportAndPassword(String idPassport, String password)

}
