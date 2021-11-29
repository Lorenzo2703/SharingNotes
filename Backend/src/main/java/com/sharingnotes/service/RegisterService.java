package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security_old.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class RegisterService {

    public MongoDb mongo= MongoDb.getConnection();
    private static final Gson gson = new Gson();

    public ResponseEntity register(String name, String email, String password){
        try{
            User user=new User((UUID.randomUUID()),name,email.toLowerCase(),password, new ArrayList<>());
            if(mongo.usedName(name) == false && mongo.usedEmail(email) == false) {
                mongo.insertUser(user);
            }
            Login login =new Login();
            if (login.authentication(email.toLowerCase(), password)){
                return ResponseEntity.ok(mongo.getUser(email.toLowerCase(),password));
            }else{
                return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
        }
    }
}
