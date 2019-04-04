package com.odinrossy.banksystem.models.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = 'users')
class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id

    @NotNull
    @Size(max = 14)
    @Column(unique = true)
    private String idPassport

    @NotNull
    @Size(max = 25)
    private String firstName

    @NotNull
    @Size(max = 25)
    private String middleName

    @NotNull
    @Size(max = 25)
    private String lastName

    @NotNull
    @Size(max = 25)
    @Column(unique = true)
    private String username

    @NotNull
    @Size(max = 10)
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
