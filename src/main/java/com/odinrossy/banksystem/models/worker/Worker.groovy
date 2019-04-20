package com.odinrossy.banksystem.models.worker

import com.odinrossy.banksystem.models.accessLevel.AccessLevel
import com.odinrossy.banksystem.models.passport.Passport
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = 'workers')
class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @NotNull
    @Column(unique = true)
    String username

    @NotNull
    @Size(max = 14)
    String password

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'access_level_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    AccessLevel accessLevel

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passport_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Passport passport

    boolean asBoolean() {
        return username && password && accessLevel && passport
    }


    @Override
    String toString() {
        return "Worker{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                ", passport=" + passport +
                '}'
    }
}
