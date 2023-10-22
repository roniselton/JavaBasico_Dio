package com.example.testeconsumoviacep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TesteConsumoViaCepApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteConsumoViaCepApplication.class, args);
    }

}
