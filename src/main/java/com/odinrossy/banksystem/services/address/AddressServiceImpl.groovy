package com.odinrossy.banksystem.services.address

import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.repositories.address.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository

    @Override
    List<Address> findAll() {
        log.debug('findAll')
        return addressRepository.findAll() as List<Address>
    }

    @Override
    List<Address> findAllByCountryAndCityAndBuildingNumber(String country, String city, short buildingNumber)
            throws ResourceNotFoundException {

        log.debug('findAllByCountryAndCityAndBuildingNumber')

        List<Address> addressList = addressRepository.findAllByCountryAndCityAndBuildingNumber(country, city, buildingNumber)

        if (addressList.size() == 0)
            throw new ResourceNotFoundException("Address not found, " +
                    "\nCountry: ${country}, " +
                    "\nCity: ${city}, " +
                    "\nBulding: ${buildingNumber}.")

        return addressList
    }

    @Override
    Address findById(long id) throws ResourceNotFoundException {
        log.debug("findById, id: ${id}.")

        Address address = addressRepository.findById(id)

        if (!address)
            throw new ResourceNotFoundException("Address not found, id: ${id}.")

        return address
    }

    @Override
    Address save(Address address) throws ResourceNotValidException {
        log.debug("save, ${address}.")

        try {
            def addressFromDB = findById(address.id)
            throw new ResourceAlreadyExistsException("Address already exists. ${addressFromDB}")

        } catch (ResourceNotFoundException ignored) {
            if (!address) {
                throw new ResourceNotValidException("Address not valid. ${address}")
            }

            address = addressRepository.save(address)
            return findById(address.id)
        }
    }

    @Override
    Address update(long id, Address address) throws ResourceNotFoundException, ResourceNotValidException {
        address.id = id
        log.debug("update, ${address}.")

        findById(address.id)

        if (!address) {
            throw new ResourceNotValidException("Address not valid. ${address}")
        }

        address = addressRepository.save(address)
        return findById(address.id)
    }

    @Override
    def delete(long id) throws ResourceNotFoundException {
        log.debug("delete, id: ${id}.")
        addressRepository.delete(findById(id))
    }
}
