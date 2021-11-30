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

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();

    /**
     * Creazione nella collection mongo di un nuovo user
     * @return
     */
    public ResponseEntity<String> insertUser(){
        mongo.insertUser(new User(UUID.randomUUID(),"name","email","password", new ArrayList<String>()));
        return ResponseEntity.ok("success");
    }

    /**
     * Ritorna il document dell'user cercato in base all'id
     * @param id
     * @return
     */
    public ResponseEntity getUserByID(String id){
        return new ResponseEntity((mongo.getDocumentByID(id,"utenti")), HttpStatus.OK);
    }
}
