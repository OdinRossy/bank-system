package com.odinrossy.banksystem.services.accessLevel

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface AccessLevelService extends LoggerService {

    List<AccessLevel> findAll()

    AccessLevel findById(short id)

    AccessLevel findByLabel(String label)

    AccessLevel save(AccessLevel accessLevel)

    AccessLevel update(short id, AccessLevel accessLevel)

    void delete(short id)

}
