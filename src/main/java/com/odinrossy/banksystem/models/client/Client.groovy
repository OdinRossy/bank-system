package com.odinrossy.banksystem.models.client

import com.odinrossy.banksystem.models.passport.Passport
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'clients')
class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Passport passport

    @NonNull
    boolean isDisabled

    @NonNull
    boolean isRetiree

    @NonNull
    boolean isBoundToMilitaryService

//    todo replace with BigDecimal
    @NonNull
    double incomePerMonth

    @Override
    String toString() {
        return "Client{" +
                "id=" + id +
                ", passport=" + passport +
                ", isDisabled=" + isDisabled +
                ", isRetiree=" + isRetiree +
                ", isBoundToMilitaryService=" + isBoundToMilitaryService +
                ", incomePerMonth=" + incomePerMonth +
                '}'
    }
}
