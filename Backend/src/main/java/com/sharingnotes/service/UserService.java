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
     * Ritorna il document dell'user cercato in base all'id
     * @param id
     * @return
     */
    public ResponseEntity getUserByID(String id){
        try {
            return new ResponseEntity((mongo.getDocumentByID(id, "utenti")), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity((mongo.getDocumentByID(id, "utenti")), HttpStatus.BAD_REQUEST);
        }
    }
}
