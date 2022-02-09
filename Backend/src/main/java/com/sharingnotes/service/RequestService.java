package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.Richiesta;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;


@Service
public class RequestService {

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /**
     * Creazione di una nuova richiesta inserendo su mongo un nuovo document
     * @param idRichiedente
     * @param title
     * @param testo
     * @return
     */
    public ResponseEntity<String> insertRichiesta(String idRichiedente, String title, String testo){
        mongo.insertRichiesta(new Richiesta(UUID.randomUUID(),idRichiedente,title,testo));
        return ResponseEntity.ok("success");
    }

    /**
     * Richiesta passa da aperta a completata
     * @param bool
     * @param id
     * @param collection
     * @return
     */
    public ResponseEntity<String> completeRequest(String bool, String id, String collection){
        try {
            mongo.completeRequest(mongo.getDocumentByID(id,collection),collection, Boolean.parseBoolean(bool));
            return new ResponseEntity<>(gson.toJson("Richiesta chiusa correttamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Richiesta non chiusa"), HttpStatus.BAD_REQUEST);
        }
    }
}
