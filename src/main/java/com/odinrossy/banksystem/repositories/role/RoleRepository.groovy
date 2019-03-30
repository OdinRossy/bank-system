package com.odinrossy.banksystem.repositories.role

import com.odinrossy.banksystem.models.role.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository extends CrudRepository<Role, String> {
}
