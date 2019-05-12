package com.odinrossy.banksystem.models.account

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'accounts')
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    String name

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'currency_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Currency currency

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'account_type_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    AccountType accountType

    @NotNull
    long number

    @NotNull
    BigDecimal value

    @NotNull
    boolean isCurrent

}
