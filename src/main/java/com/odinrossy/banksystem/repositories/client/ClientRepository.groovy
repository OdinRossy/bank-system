package com.odinrossy.banksystem.repositories.client

import com.odinrossy.banksystem.models.client.Client
import org.springframework.stereotype.Repository

@Repository
class ClientRepository {

    private static List<Client> clientList = new ArrayList<>()

    static {
        clientList.add(new Client(idPassport: '1', lastName: 'Remniov', firstName: 'Gleb', middleName: 'Alexanfrovich'))
        clientList.add(new Client(idPassport: '2', lastName: 'Kovalevick', firstName: 'Alina', middleName: 'Andreevna'))
        clientList.add(new Client(idPassport: '3', lastName: 'Semenov', firstName: 'Fanial', middleName: 'Mihailovich'))
        clientList.add(new Client(idPassport: '4', lastName: 'Ivanov', firstName: 'Igor', middleName: 'Petrovich'))
    }

    List<Client> findAll(){
        return clientList
    }

    Client findByIdPassport(String idPassport){
        for (Client client : clientList) {
            if (client.idPassport == idPassport)
                return client
        }
        return null
    }

}
