package com.odinrossy.banksystem.services.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
interface LoggerService {

    final static Logger log = LoggerFactory.getLogger(this.class)

}