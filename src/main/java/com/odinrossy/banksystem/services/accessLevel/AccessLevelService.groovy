package com.odinrossy.banksystem.services.accessLevel

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import org.springframework.stereotype.Service

@Service
interface AccessLevelService {

    List<AccessLevel> findAll()

    AccessLevel findById(short id)

    AccessLevel findByLabel(String label)

    AccessLevel save(AccessLevel accessLevel)

    AccessLevel update(short id, AccessLevel accessLevel)

    void delete(short id)

}
