package com.sharingnotes.Controller;

import com.sharingnotes.Security.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public ResponseEntity<String> authentication(@RequestParam("email")String email, @RequestParam("password")String password){
        try{
        Login login =new Login();
        if (login.authentication(email.toLowerCase(), password)){
            return ResponseEntity.ok("Login Successful");
        }else{
            return new ResponseEntity<>("New there? Please Register", HttpStatus.FORBIDDEN);
        }}catch (Exception e){
            return new ResponseEntity<>("New there? Please Register", HttpStatus.FORBIDDEN);
        }
    }
}
