package com.odinrossy.banksystem.repositories.account

import com.odinrossy.banksystem.models.account.Contract
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContractRepository extends CrudRepository<Contract,Long> {

    Contract findById(long id)

    List<Contract> findAllByClient(Client client)

    List<Contract> findAllByWorker(Worker worker)

}
