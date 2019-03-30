package com.odinrossy.banksystem.models.user

import javax.persistence.Entity
import javax.persistence.Id
import java.util.Date

@Entity
class User {

    @Id
    private String idPassport

    private String firstName
    private String middleName
    private String lastName

    private String username
    private String password

    private long idRole

    private Date birthDate

    boolean asBoolean() {
        return idPassport && firstName && middleName && lastName && username && password && idRole && birthDate
    }

    User() {
    }

    User(String idPassport, String password) {
        this.idPassport = idPassport
        this.password = password
    }

    User(String idPassport, String firstName, String middleName, String lastName, String username, String password, long idRole, Date birthDate) {
        this.idPassport = idPassport
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.username = username
        this.password = password
        this.idRole = idRole
        this.birthDate = birthDate
    }

    @Override
    String toString() {
        return "User{" +
                "idPassport='" + idPassport + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", idRole=" + idRole +
                ", birthDate=" + birthDate +
                '}'
    }

    long getIdRole() {
        return idRole
    }

    void setIdRole(long idRole) {
        this.idRole = idRole
    }

    String getIdPassport() {
        return idPassport
    }

    void setIdPassport(String idPassport) {
        this.idPassport = idPassport
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getMiddleName() {
        return middleName
    }

    void setMiddleName(String middleName) {
        this.middleName = middleName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Date getBirthDate() {
        return birthDate
    }

    void setBirthDate(Date birthDate) {
        this.birthDate = birthDate
    }
}
