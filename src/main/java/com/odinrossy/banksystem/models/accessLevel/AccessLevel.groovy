package com.odinrossy.banksystem.models.accessLevel

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'access_levels')
class AccessLevel {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    short id

    @NotNull
    @Column(unique = true)
    String label

    @Override
    String toString() {
        return "AccessLevel{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}'
    }
}
