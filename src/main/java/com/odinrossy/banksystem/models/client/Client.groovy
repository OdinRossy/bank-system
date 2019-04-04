package com.odinrossy.banksystem.models.client

import javax.persistence.Id
import java.util.Date

class Client implements Serializable{

    String idPassport
    String firstName
    String middleName
    String lastName

    boolean asBoolean() {
        return idPassport && firstName && middleName && lastName
    }

    Client() {
    }

    Client(String idPassport, String firstName, String middleName, String lastName) {
        this.idPassport = idPassport
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
    }

    @Override
    String toString() {
        return "Client{" +
                "idPassport='" + idPassport + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}'
    }
}