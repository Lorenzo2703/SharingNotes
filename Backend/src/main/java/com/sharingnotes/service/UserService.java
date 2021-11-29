package com.sharingnotes.service;

import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {

    public MongoDb mongo= MongoDb.getConnection();

    public ResponseEntity<String> insertUser(){
        mongo.insertUser(new User(UUID.randomUUID(),"name","email","password", new ArrayList<String>()));
        return ResponseEntity.ok("success");
    }

    public ResponseEntity getUserByID(String id){
        return new ResponseEntity((mongo.getUserByID(id)), HttpStatus.OK);
    }
}
