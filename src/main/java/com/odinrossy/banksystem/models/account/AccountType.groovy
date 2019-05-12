package com.odinrossy.banksystem.models.account

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'account_types')
class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    String name

}
