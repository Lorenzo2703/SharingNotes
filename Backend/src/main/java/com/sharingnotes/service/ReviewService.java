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

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /**
     * Inserisco una nuova reconsione della nota
     * @param idRecensore
     * @param idUserRecensito
     * @param idNotaRecensita
     * @param title
     * @param testo
     * @return
     */
    public ResponseEntity<String> insert(String idRecensore, String idUserRecensito, String idNotaRecensita, String title, String testo) {
        mongo.insertRecensione(new Recensione(UUID.randomUUID(), idRecensore, idUserRecensito, idNotaRecensita, title, testo));
        try {
            return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Unuccess"), HttpStatus.BAD_REQUEST);
        }
    }
}
