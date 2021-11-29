package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.Recensione;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewService {

    public MongoDb mongo=new MongoDb();
    private static final Gson gson = new Gson();

    public ResponseEntity<String> insert(String idRecensore, String idUserRecensito, String idNotaRecensita, String title, String testo) {
        mongo.insertRecensione(new Recensione(UUID.randomUUID(), idRecensore, idUserRecensito, idNotaRecensita, title, testo));
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }
}
