package com.odinrossy.banksystem.models.address

import com.odinrossy.banksystem.models.client.Client
import com.odinrossy.banksystem.models.country.Country
import com.odinrossy.banksystem.models.registration.Registration
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'addresses')
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'country_iso3code')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Country country

    @NonNull
    String city

    @NonNull
    String street

    @NonNull
    short buildingNumber

    @NonNull
    boolean isApartment

    short apartmentNumber

    @NonNull
    int postCode

    @OneToMany(mappedBy = 'address')
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Registration> registrationList

    @OneToMany(mappedBy = 'address')
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Client> clientList

    @Override
    String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", isApartment=" + isApartment +
                ", apartmentNumber=" + apartmentNumber +
                ", postCode=" + postCode +
                '}'
    }
}
