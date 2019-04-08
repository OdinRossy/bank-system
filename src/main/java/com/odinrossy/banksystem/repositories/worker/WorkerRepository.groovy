package com.odinrossy.banksystem.repositories.worker

import com.odinrossy.banksystem.models.worker.Worker
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkerRepository extends CrudRepository<Worker, Long> {

    Worker findById(long id)

    Worker findByUsername(String username)

    Worker findByUsernameAndPassword(String username, String password)

}
