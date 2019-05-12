package com.odinrossy.banksystem.models.operation

import com.odinrossy.banksystem.models.account.Account
import com.odinrossy.banksystem.models.worker.Worker
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'operations')
class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'operation_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    OperationType operationType

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'account_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Account account

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'worker_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Worker worker

    @NotNull
    Date dateOfIssue

    @NotNull
    Date dateOfExpire

    @NotNull
    byte percentage

}
