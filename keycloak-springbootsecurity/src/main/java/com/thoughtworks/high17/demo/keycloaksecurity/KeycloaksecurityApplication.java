package com.thoughtworks.high17.demo.keycloaksecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by hwwei on 2017/2/24.
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class KeycloaksecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeycloaksecurityApplication.class, args);
    }

}
