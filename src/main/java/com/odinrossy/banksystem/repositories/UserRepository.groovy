package com.odinrossy.banksystem.repositories

import com.odinrossy.banksystem.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends CrudRepository<User, String> {

}
