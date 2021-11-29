package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security_old.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public MongoDb mongo= new MongoDb();
    private static final Gson gson = new Gson();

    public ResponseEntity authentication(String email,String password){
        try{
            Login login =new Login();
            if (login.authentication(email.toLowerCase(), password)){
                return ResponseEntity.ok(mongo.getUser(email.toLowerCase(),password));
            }else{
                return new ResponseEntity<>(gson.toJson("New there? Please Register"), HttpStatus.FORBIDDEN);
            }}catch (Exception e){
            return new ResponseEntity<>(gson.toJson("New there? Please Register"), HttpStatus.FORBIDDEN);
        }
    }
}
