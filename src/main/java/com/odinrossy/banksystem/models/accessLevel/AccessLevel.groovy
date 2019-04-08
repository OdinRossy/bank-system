package com.odinrossy.banksystem.models.accessLevel


import com.odinrossy.banksystem.models.worker.Worker
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

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

    @OneToMany(mappedBy = 'accessLevel')
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Worker> workers

//    boolean asBoolean() {
//        return id && label
//    }


    @Override
    String toString() {
        return "AccessLevel{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}'
    }
}
