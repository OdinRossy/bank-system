package com.odinrossy.banksystem.repositories.accessLevel

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccessLevelRepository extends CrudRepository<AccessLevel, Short> {

    AccessLevel findById(short id)

    AccessLevel findByLabel(String label)

}
