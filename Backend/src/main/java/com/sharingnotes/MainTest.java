package com.sharingnotes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;


@CrossOrigin()
@RestController
@SpringBootApplication
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
    }
}

