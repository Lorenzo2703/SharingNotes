package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.sharingnotes.Model.GroupChat;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin()
public class ScoreController {

    public MongoDb mongo=new MongoDb();
    private static final Gson gson = new Gson();

    @PostMapping(value = "/updateScore")
    public ResponseEntity<String> updateScore(@RequestParam("score") String score, @RequestParam("id")String id,@RequestParam("collection") String collection){
        try {
            mongo.updateScore(mongo.getDocumentByID(id,collection),collection,Integer.parseInt(score));
            return new ResponseEntity<>(gson.toJson("Score aggiornato! con media: "+mongo.getDocumentByID(id,collection).getDouble("rating")), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Score non aggiornato"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/insertIdVotati")
    public ResponseEntity<String> insertIdVotati(@RequestParam("id_votato") String id_votato, @RequestParam("id")String id){
        try {
            Document documento = mongo.getUserByID(id);
            User user = new User(UUID.fromString(documento.get("_id").toString()), (String) documento.get("name"), (String) documento.get("email"), (String) documento.get("password"));
            mongo.insertIdVotati(user,id_votato);
            return new ResponseEntity<>(gson.toJson("Id_votato aggiornato"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Id_votato NON aggiornato"), HttpStatus.BAD_REQUEST);
        }
    }

}
