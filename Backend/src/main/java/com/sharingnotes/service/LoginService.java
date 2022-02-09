package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class LoginService {

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /**
     * Autenticazione della login cercando la corrispondenza tra email e password
     * @param email
     * @param password
     * @return
     */
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
