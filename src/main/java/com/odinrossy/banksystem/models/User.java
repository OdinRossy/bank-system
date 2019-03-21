package com.odinrossy.banksystem.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {

    @Id
    private String idPassport;

    private String firstName;
    private String middleName;
    private String lastName;

    private String username;
    private String password;

    public User() {
    }

    public User(String idPassport, String password) {
        this.idPassport = idPassport;
        this.password = password;
    }

    public User(String idPassport, String firstName, String middleName, String lastName, String username, String password) {
        this.idPassport = idPassport;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "idPassport='" + idPassport + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(String idPassport) {
        this.idPassport = idPassport;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

//    private Date birthDate;
//
//    private boolean isMale;
//    private String address;
//
//    private String mobileNumber;
//
//    private String phoneNumber;
//
//    private String placeOfWork;
//
//    private boolean hasDisability;
//
//    private boolean isRetiree;
//
//    private double incomePerMonth;
//
//    private boolean isMilitaryObliged;
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id_passport=" + idPassport +
//                ", firstName='" + firstName + '\'' +
//                ", middleName='" + middleName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", birthDate=" + birthDate +
//                ", isMale=" + isMale +
//                ", address='" + address + '\'' +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", placeOfWork='" + placeOfWork + '\'' +
//                ", hasDisability=" + hasDisability +
//                ", isRetiree=" + isRetiree +
//                ", incomePerMonth=" + incomePerMonth +
//                ", isMilitaryObliged=" + isMilitaryObliged +
//                '}';
//    }
//
//    public User() {
//    }
//
//    public User(String idPassport, String firstName, String middleName, String lastName, String username,
//                String password, Date birthDate, boolean isMale, String address, String mobileNumber,
//                String phoneNumber, String placeOfWork, boolean hasDisability, boolean isRetiree,
//                double incomePerMonth, boolean isMilitaryObliged) {
//        this.idPassport = idPassport;
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.username = username;
//        this.password = password;
//        this.birthDate = birthDate;
//        this.isMale = isMale;
//        this.address = address;
//        this.mobileNumber = mobileNumber;
//        this.phoneNumber = phoneNumber;
//        this.placeOfWork = placeOfWork;
//        this.hasDisability = hasDisability;
//        this.isRetiree = isRetiree;
//        this.incomePerMonth = incomePerMonth;
//        this.isMilitaryObliged = isMilitaryObliged;
//    }
//
//    public String getIdPassport() {
//        return idPassport;
//    }
//
//    public void setIdPassport(String id_passport) {
//        this.idPassport = id_passport;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public boolean isMale() {
//        return isMale;
//    }
//
//    public void setMale(boolean male) {
//        isMale = male;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getPlaceOfWork() {
//        return placeOfWork;
//    }
//
//    public void setPlaceOfWork(String placeOfWork) {
//        this.placeOfWork = placeOfWork;
//    }
//
//    public boolean isHasDisability() {
//        return hasDisability;
//    }
//
//    public void setHasDisability(boolean hasDisability) {
//        this.hasDisability = hasDisability;
//    }
//
//    public boolean isRetiree() {
//        return isRetiree;
//    }
//
//    public void setRetiree(boolean retiree) {
//        isRetiree = retiree;
//    }
//
//    public double getIncomePerMonth() {
//        return incomePerMonth;
//    }
//
//    public void setIncomePerMonth(double incomePerMonth) {
//        this.incomePerMonth = incomePerMonth;
//    }
//
//    public boolean isMilitaryObliged() {
//        return isMilitaryObliged;
//    }
//
//    public void setMilitaryObliged(boolean militaryObliged) {
//        isMilitaryObliged = militaryObliged;
//    }
//}
