package com.odinrossy.banksystem.models.registration

import com.odinrossy.banksystem.models.address.Address
import com.odinrossy.banksystem.models.passport.Passport
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import org.springframework.lang.NonNull

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = 'registrations')
class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    @NotNull
    @ManyToOne
    @JoinColumn(name = 'address_id')
    @OnDelete(action = OnDeleteAction.CASCADE)
    Address address

    @NonNull
    Date dateOfRegistration

    @NonNull
    String registrationAuthority

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "registration")
    private Passport passport


    @Override
    String toString() {
        return "Registration{" +
                "id=" + id +
                ", address.Address=" + address +
                ", dateOfRegistration=" + dateOfRegistration +
                ", registrationAuthority='" + registrationAuthority + '\'' +
                '}'
    }
}
