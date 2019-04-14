package com.odinrossy.banksystem.services.address

import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AddressService extends LoggerService {

    List<Address> findAll()

    List<Address> findAllByCountryAndCityAndBuildingNumber(String country, String city, short buildingNumber)
            throws ResourceNotFoundException

    Address findById(long id) throws ResourceNotFoundException

    Address save(Address address) throws ResourceNotValidException

    Address update(long id, Address address) throws ResourceNotFoundException, ResourceNotValidException

    def delete(long id) throws ResourceNotFoundException

}
