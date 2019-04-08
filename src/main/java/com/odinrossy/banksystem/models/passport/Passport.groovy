package com.odinrossy.banksystem.models.passport

import com.odinrossy.banksystem.models.worker.Worker

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = 'passports')
class Passport {

    @Id
    @NotNull
    @Column(unique = true)
    @Size(max = 14)
    String id

    @NotNull
    String lastName

    @NotNull
    String firstName

    @NotNull
    String middleName

    @NotNull
    String series

    @NotNull
    @Column(unique = true)
    int number

    @NotNull
    Date dateOfIssue

    @NotNull
    Date dateOfExpire

    @NotNull
    String countryName

    @NotNull
    boolean isMale

    @NotNull
    Date birthDate

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "passport")
    private Worker worker

    @Override
    String toString() {
        return "Passport{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpire=" + dateOfExpire +
                ", countryName='" + countryName + '\'' +
                ", isMale=" + isMale +
                ", birthDate=" + birthDate +
                '}'
    }
}