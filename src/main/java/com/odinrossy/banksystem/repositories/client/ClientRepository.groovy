package com.odinrossy.banksystem.repositories.client

import com.odinrossy.banksystem.models.client.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(long id)

    Client findByPassportId(String passportId)

}
