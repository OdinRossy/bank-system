package com.odinrossy.banksystem.services.account.implementation

import com.odinrossy.banksystem.config.BankConfig
import com.odinrossy.banksystem.exceptions.ResourceAlreadyExistsException
import com.odinrossy.banksystem.exceptions.ResourceNotFoundException
import com.odinrossy.banksystem.exceptions.ResourceNotValidException
import com.odinrossy.banksystem.models.account.Contract
import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import com.odinrossy.banksystem.repositories.account.ContractRepository
import com.odinrossy.banksystem.services.account.abstraction.ContractService
import com.odinrossy.banksystem.services.security.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository

    @Autowired
    AuthorizationService authorizationService

    @Override
    Contract findById(long id) {
        Contract contract = contractRepository.findById(id)
        if (!contract)
            throw new ResourceNotFoundException('Contract with id: ' + id + ' not found.')

        return contract
    }

    @Override
    List<Contract> findAll() {
        return contractRepository.findAll() as List<Contract>
    }

    @Override
    List<Contract> findAllByClient(Client client) {
        return contractRepository.findAllByClient(client)
    }

    @Override
    List<Contract> findAllByWorker(Worker worker) {
        return contractRepository.findAllByWorker(worker)
    }

    @Override
    Contract save(Contract contract) {
        try {

            contract.worker = authorizationService.workerFromSession

            setDateOfIssue(contract)
            setDateOfExpire(contract, BankConfig.CONTRACT_DURANCE)

            findById(contract.id)
            throw new ResourceAlreadyExistsException('Contract already exists. Details: ' + contract)

        } catch (ResourceNotFoundException ignored) {
            if (!contract)
                throw new ResourceNotValidException('Contract is not valid')

            contract = contractRepository.save(contract)
            return findById(contract.id)
        }
    }

    @Override
    Contract update(long id, Contract contract) {
        return null
    }

    @Override
    def delete(long id) {
        return contractRepository.delete(findById(id))
    }

    @Override
    def setDateOfIssue(Contract contract) {
        contract.dateOfIssue = new Date()
    }

    @Override
    def setDateOfExpire(Contract contract, int years) {
        if (!contract.dateOfIssue) {
            contract.setDateOfIssue()
        }
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, years)
        contract.dateOfExpire = calendar.getTime()
    }
}
