package com.sharingnotes;

import com.google.gson.Gson;
import com.sharingnotes.Cloud.CloudApi;
import com.sharingnotes.Model.*;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

@CrossOrigin()
@RestController
@SpringBootApplication
public class MainTest {
    public MongoDb mongo=new MongoDb();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
    }

    @PreDestroy
    public void destroy(){
        mongo.destroy();
    }


    @RequestMapping(value = "/insertRecensione",method = RequestMethod.POST)
    public ResponseEntity<String> insertRecensione(@RequestParam("idRecensore") String idRecensore,@RequestParam("idUserRecensito") String idUserRecensito,@RequestParam("idNotaRecensita") String idNotaRecensita,@RequestParam("title") String title,@RequestParam("testo") String testo){
        mongo.insertRecensione(new Recensione(UUID.randomUUID(), idRecensore, idUserRecensito, idNotaRecensita, title, testo));
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertRichiesta",method = RequestMethod.POST)
    public ResponseEntity<String> insertRichiesta(@RequestParam("idRichiedente") String idRichiedente,@RequestParam("title") String title, @RequestParam("testo") String testo){
        mongo.insertRichiesta(new Richiesta(UUID.randomUUID(),idRichiedente,title,testo));
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public ResponseEntity<String> insertUser(){
        mongo.insertUser(new User(UUID.randomUUID(),"name","email","password", new ArrayList<String>()));
        return ResponseEntity.ok("success");
    }



    @RequestMapping(value = "/getUserByID",method = RequestMethod.GET)
    public ResponseEntity getUserByID(@RequestParam("_id") String id){
        return new ResponseEntity((mongo.getUserByID(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/completeRequest", method = RequestMethod.POST)
    public ResponseEntity<String> completeRequest(@RequestParam("bool") String bool, @RequestParam("id")String id,@RequestParam("collection") String collection){
        try {
            mongo.completeRequest(mongo.getDocumentByID(id,collection),collection, Boolean.parseBoolean(bool));
            return new ResponseEntity<>(gson.toJson("Richiesta chiusa correttamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Richiesta non chiusa"), HttpStatus.BAD_REQUEST);
        }
    }



}

