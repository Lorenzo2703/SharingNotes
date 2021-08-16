package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.mongodb.util.JSON;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class LoginController {

    private static final Gson gson = new Gson();

    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authentication(@RequestParam("email")String email, @RequestParam("password")String password){
        try{
        Login login =new Login();
        if (login.authentication(email.toLowerCase(), password)){
            return ResponseEntity.ok(new MongoDb().getUser(email.toLowerCase(),password));
        }else{
            return new ResponseEntity<>(gson.toJson("New there? Please Register"), HttpStatus.FORBIDDEN);
        }}catch (Exception e){
            return new ResponseEntity<>(gson.toJson("New there? Please Register"), HttpStatus.FORBIDDEN);
        }
    }
}
