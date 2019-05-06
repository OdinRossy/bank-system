package com.odinrossy.banksystem.models.account


import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'currencies')
class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    @Column(unique = true)
    String name
}
