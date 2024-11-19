package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableAsync
@SpringBootApplication
@RestController
public class ProjetArchilogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetArchilogApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }


}


