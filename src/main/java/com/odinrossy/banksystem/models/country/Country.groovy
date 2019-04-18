package com.odinrossy.banksystem.models.country

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = 'countries')
class Country {

    @Id
    @NotNull
    @Size(min = 3, max = 3)
    @Column(unique = true)
    String iso3code

    @NotNull
    @Column(unique = true)
    String countryName

    @Override
    String toString() {
        return "Country{" +
                "iso3code='" + iso3code + '\'' +
                ", countryName='" + countryName + '\'' +
                '}'
    }
}
