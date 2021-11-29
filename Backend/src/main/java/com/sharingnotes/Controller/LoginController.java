package com.sharingnotes.Controller;

import com.sharingnotes.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/authenticate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authentication(@RequestParam("email")String email, @RequestParam("password")String password) {
        return loginService.authentication(email, password);
    }
}
