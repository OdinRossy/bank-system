package com.odinrossy.banksystem.models.passport

import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.models.registration.Registration
import com.odinrossy.banksystem.models.worker.Worker
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
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
    @ManyToOne
    @JoinColumn(name = 'country_iso3code')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country citizenship

    @NonNull
    String birthPlace

    @NotNull
    boolean isMale

    @NotNull
    Date birthDate

    @NonNull
    boolean isMarried

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "passport")
    private Worker worker

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "passport")
    private Client client

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registration_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Registration registration

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
                ", citizenship=" + citizenship +
                ", birthPlace='" + birthPlace + '\'' +
                ", isMale=" + isMale +
                ", birthDate=" + birthDate +
                ", isMarried=" + isMarried +
                ", registration=" + registration +
                '}'
    }
}
