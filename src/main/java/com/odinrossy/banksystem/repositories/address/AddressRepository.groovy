package com.odinrossy.banksystem.repositories.address

import com.odinrossy.banksystem.models.address.Address
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository extends CrudRepository<Address, Long> {

    Address findById(long id)

    List<Address> findAllByCountryAndCityAndBuildingNumber(String country, String city, short buildingNumber)

}