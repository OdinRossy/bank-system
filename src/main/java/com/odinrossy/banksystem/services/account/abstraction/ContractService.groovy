package com.odinrossy.banksystem.services.account.abstraction

import com.odinrossy.banksystem.models.account.Contract
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.services.logger.LoggerService
import org.springframework.stereotype.Service

@Service
interface ContractService extends LoggerService {

    Contract findById(long id)

    List<Contract> findAll()

    List<Contract> findAllByClient(Client client)

    List<Contract> findAllByWorker(Worker worker)

    Contract save(Contract contract)

    Contract update(long id, Contract contract)

    def delete(long id)

    def setDateOfIssue(Contract contract)

    def setDateOfExpire(Contract contract, int years)

}
