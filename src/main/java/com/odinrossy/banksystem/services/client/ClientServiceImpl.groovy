package com.odinrossy.banksystem.services.client

import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.repositories.client.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository

    @Autowired
    ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository
    }

    List<Client> findAll(){
        return clientRepository.findAll()
    }

    Client findByIdPassport(String idPassport){
        return clientRepository.findByIdPassport(idPassport)
    }
}
