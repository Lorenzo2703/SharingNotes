package com.sharingnotes;

import com.sharingnotes.MongoDb.MongoConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MainTest implements MongoConnect {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
    }

    @RequestMapping("/hi")
    public String Ciao(){
        return "<h1>CIAO</h1>";
    }

    @Override
    public void connection() {
        super(this);
    }
}
