package com.odinrossy.banksystem.repositories.address

import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.country.Country
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository extends CrudRepository<Address, Long> {

    Address findById(long id)

    Address findByCountryAndCityAndStreetAndBuildingNumberAndApartmentNumberAndPostCode(Country country, String city, String street, short buildingNumber, short apartmentNumber, int postCode)

}