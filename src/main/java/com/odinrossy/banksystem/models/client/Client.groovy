package com.odinrossy.banksystem.models.client

import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.models.passport.Passport
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'clients')
class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'country_iso3code')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country birthplace

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passport_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Passport passport

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'address_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Address address

    @Column(unique = true)
    @NotNull
    String mobilePhoneNumber

    @NotNull
    String homePhoneNumber

    @Column(unique = true)
    @NotNull
    String email

    @NonNull
    boolean isDisabled

    @NonNull
    boolean isRetiree

    @NonNull
    boolean isBoundToMilitaryService

    @NotNull
    boolean isEmployed

    String companyName

    String position

//    TODO: replace with BigDecimal
    @NonNull
    double incomePerMonth

    @Override
    String toString() {
        return "Client{" +
                "id=" + id +
                ", birthplace=" + birthplace +
                ", passport=" + passport +
                ", address=" + address +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", isDisabled=" + isDisabled +
                ", isRetiree=" + isRetiree +
                ", isBoundToMilitaryService=" + isBoundToMilitaryService +
                ", isEmployed=" + isEmployed +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", incomePerMonth=" + incomePerMonth +
                '}'
    }
}
