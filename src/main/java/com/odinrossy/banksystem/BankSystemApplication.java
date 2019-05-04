package com.odinrossy.banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankSystemApplication {


    private final static Logger log = LoggerFactory.getLogger(BankSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BankSystemApplication.class, args);
        String url = "http://localhost:9090/bank-system";
        log.info(url);
    }

}
