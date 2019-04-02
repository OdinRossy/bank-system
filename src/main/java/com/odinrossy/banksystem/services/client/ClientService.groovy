package com.odinrossy.banksystem.services.client

import com.odinrossy.banksystem.models.client.Client
import org.springframework.stereotype.Service

@Service
interface ClientService {

    List<Client> findAll()

    Client findByIdPassport(String idPassport)

}
