package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
