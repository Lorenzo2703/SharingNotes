package com.sharingnotes.Controller;

import com.sharingnotes.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password){
        return registerService.register(name, email, password);
    }
}
