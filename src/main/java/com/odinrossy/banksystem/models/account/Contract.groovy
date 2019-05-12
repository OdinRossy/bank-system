package com.odinrossy.banksystem.models.account

import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.worker.Worker
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'contracts')
class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'current_account_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Account current_account

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'percent_account_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Account percent_account

    @NotNull
    Date dateOfIssue

    @NotNull
    Date dateOfExpire

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'client_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Client client

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'worker_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Worker worker

    @NotNull
    double percentage

}
