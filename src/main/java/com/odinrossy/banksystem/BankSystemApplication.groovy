package com.odinrossy.banksystem

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BankSystemApplication {

    static def log = LoggerFactory.getLogger(this.class)
    static def url = 'http://localhost:9090/bank-system'

    static void main(String[] args) {
        SpringApplication.run(BankSystemApplication.class, args)
        log.info(url)
    }

}
