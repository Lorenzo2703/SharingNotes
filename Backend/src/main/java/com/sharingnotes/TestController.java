package com.sharingnotes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
            return ResponseEntity.ok("CIAO MONDO!!");

    }

}
