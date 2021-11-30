package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ScoreService {

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /**
     * Modifico lo score complessivo della nota o dell'utente
     * @param score
     * @param id
     * @param collection
     * @return
     */
    public ResponseEntity<String> updateScore( String score,String id, String collection){
        try {
            mongo.updateScore(mongo.getDocumentByID(id,collection),collection,Integer.parseInt(score));
            return new ResponseEntity<>(gson.toJson("Score aggiornato! con media: "+mongo.getDocumentByID(id,collection).getDouble("rating")), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Score non aggiornato"), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Inserisco l'id della nota o dell'user che ho votato alla lista
     * degli id di chi ho votato nel mio document
     * @param id_votato
     * @param id
     * @return
     */
    public ResponseEntity<String> insertIdVotati(String id_votato,String id){
        try {
            //get del mio documento
            Document documento = mongo.getDocumentByID(id,"utenti");
            //aggiorno l'array degli id che ho votato creando un nuovo user
            User user = new User(UUID.fromString(documento.get("_id").toString()), (String) documento.get("name"), (String) documento.get("email"), (String) documento.get("password"), (ArrayList<String>) documento.get("id_votati"));
            mongo.insertIdVotati(user,id_votato);
            return new ResponseEntity<>(gson.toJson("Id_votato aggiornato"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Id_votato NON aggiornato"), HttpStatus.BAD_REQUEST);
        }
    }
}
