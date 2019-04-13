package com.odinrossy.banksystem.models.client

import com.odinrossy.banksystem.models.passport.Passport
import org.springframework.lang.NonNull

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    long id

    Passport passport



}
