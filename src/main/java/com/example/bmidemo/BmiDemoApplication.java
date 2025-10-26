package com.example.bmidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.example.bmidemo.model")
@SpringBootApplication
public class BmiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmiDemoApplication.class, args);
    }

}
