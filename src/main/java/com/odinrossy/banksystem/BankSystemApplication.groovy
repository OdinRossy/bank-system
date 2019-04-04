package com.odinrossy.banksystem

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BankSystemApplication {

    private final static Logger logger = LoggerFactory.getLogger(BankSystemApplication.class)

    private final static url = 'http://localhost:9090/bank-system'

    static void main(String[] args) {
        SpringApplication.run(BankSystemApplication.class, args)
        logger.info(url)
    }

}