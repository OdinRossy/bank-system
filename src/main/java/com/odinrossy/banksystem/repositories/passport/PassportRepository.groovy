package com.odinrossy.banksystem.repositories.passport

import com.odinrossy.banksystem.models.passport.Passport
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PassportRepository extends CrudRepository<Passport, String>{

    Passport findByNumber(int number)

}
