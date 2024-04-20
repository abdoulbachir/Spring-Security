package com.eazybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableJpaRepositories("location of jpa repository")        If jpa interface is outside main package
//@EntityScan("location of entity class")                     If entity class outside of main package
//@EnableWebSecurity        Only need if building application using Spring without Spring boot
public class EasyBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBankBackendApplication.class, args);
    }

}
