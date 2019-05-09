package com.odinrossy.banksystem.services.client

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface ClientService extends LoggerService {

    List<Client> findAll()

    Client findById(long id) throws ResourceNotFoundException

    Client findByPassportId(id) throws ResourceNotFoundException

    Client save(Client client) throws ResourceAlreadyExistsException, ResourceNotValidException

    Client update(long id, Client client) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

    def isEmailInUse(String email)

    def isMobilePhoneNumberInUse(String mobilePhoneNumber)

    def getInitials(Client client)

}