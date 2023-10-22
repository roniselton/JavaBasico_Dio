package com.example.jwtspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtSpringApplication {



    public static void main(String[] args) {
        SpringApplication.run(JwtSpringApplication.class, args);
    }

}
