package com.odinrossy.banksystem.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service(value = 'ApplicationService')
interface ApplicationService {

    final static Logger log = LoggerFactory.getLogger(this.class)

}