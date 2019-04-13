package com.odinrossy.banksystem.repositories.registration

import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.registration.Registration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RegistrationRepository extends CrudRepository<Registration, Long> {

    Registration findById(long id)

    List<Registration> findAllByAddress(Address address)

}