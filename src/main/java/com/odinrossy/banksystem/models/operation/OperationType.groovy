package com.odinrossy.banksystem.models.operation

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'operation_types')
class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    @Column(unique = true)
    String name

}
