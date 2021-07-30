package com.sharingnotes.Controller;

import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @PostMapping(value = "/updateScore")
    public ResponseEntity<String> updateScore(@RequestParam("score") int score, @RequestParam("document")String document,@RequestParam("collection") String collection){
        try {
            MongoDb mongoDb=new MongoDb();
            Document document1= Document.parse(document);
            mongoDb.updateScore(document1,collection,score);
            System.out.println("MEDIA VOTI: "+avgScore(document1));
            return ResponseEntity.ok("Score aggiornato!");
        }catch (Exception e){
            return new ResponseEntity<>("Score non aggiornato", HttpStatus.BAD_REQUEST);
        }
    }

    public float avgScore(Document document){
        int rating=document.getInteger("rating");
        int nvoti=document.getInteger("nvoti");
        float media=0.0f;
        try {
            media = Math.round(rating/ nvoti);
        }catch (Exception e){
            e.printStackTrace();
        }
        return media;
    }
}
