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
import java.util.UUID;

@CrossOrigin()
@RestController
@SpringBootApplication
public class MainTest {
    public MongoDb mongo=new MongoDb();
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
        JSONObject json = new JSONObject(CloudApi.getAll()); // Convert text to object
        System.out.println(json.toString(5));


    }

    @PreDestroy
    public void destroy(){
        mongo.destroy();
    }

    @GetMapping("/getAllFile")
    public String getAll(){
        return (CloudApi.getAll().toString());
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("userId") String id) {
        CloudApi.uploadFile(convertFile(multipartFile),title,description,id);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertRecensione",method = RequestMethod.POST)
    public ResponseEntity<String> insertRecensione(@RequestParam("idRecensore") String idRecensore,@RequestParam("idUserRecensito") String idUserRecensito,@RequestParam("idNotaRecensita") String idNotaRecensita,@RequestParam("title") String title,@RequestParam("testo") String testo){
        mongo.insertRecensione(new Recensione(UUID.randomUUID(), idRecensore, idUserRecensito, idNotaRecensita, title, testo));
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertRichiesta",method = RequestMethod.POST)
    public ResponseEntity<String> insertRichiesta(){
        mongo.insertRichiesta(new Richiesta(UUID.randomUUID(),"1234","title","testo"));
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public ResponseEntity<String> insertUser(){
        mongo.insertUser(new User(UUID.randomUUID(),"name","email","password"));
        return ResponseEntity.ok("success");
    }


    @RequestMapping(value = "/getUrlFiles",method = RequestMethod.GET)
    public ResponseEntity<String> getUrlfiles(){
        return new ResponseEntity((mongo.getUrlFiles()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFiles",method = RequestMethod.GET)
    public ResponseEntity getFiles(@RequestParam("collection")String collection){
        return new ResponseEntity((mongo.getAllFilesInCollection(collection)), HttpStatus.OK);
    }

    public File convertFile(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : null;
        File file;
        try {
            file = File.createTempFile(fileName.substring(0,fileName.lastIndexOf("."))
                    .replaceAll("[^a-zA-Z0-9-_\\.]", "_"), prefix);
            multipartFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

