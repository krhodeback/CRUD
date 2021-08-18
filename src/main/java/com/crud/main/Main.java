package com.crud.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.crud.config", "com.crud.controller","com.crud.service"})
@EnableJpaRepositories("com.crud.dao")
@EntityScan("com.crud.entity")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
