package com.sharingnotes;

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
import java.io.File;
import java.util.HashMap;
import java.util.UUID;


@RestController
@SpringBootApplication
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
        JSONObject json = new JSONObject(CloudApi.getAll()); // Convert text to object
        System.out.println(json.toString(5));
    }

    @GetMapping("/getAllFile")
    public String getAll(){
        return (CloudApi.getAll().toString());
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("userId") String id) {
        CloudApi.uploadFile(convertFile(multipartFile),title,description,id);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping(value = "/insertRecensione",method = RequestMethod.POST)
    public ResponseEntity<String> insertRecensione(){
        new MongoDb().insertRecensione(new Recensione(UUID.randomUUID(),"1234","4321","1423","title","testo"));
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/insertRichiesta",method = RequestMethod.POST)
    public ResponseEntity<String> insertRichiesta(){
        new MongoDb().insertRichiesta(new Richiesta(UUID.randomUUID(),"1234","title","testo"));
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public ResponseEntity<String> insertUser(){
        new MongoDb().insertUser(new User(UUID.randomUUID(),"name","email","password"));
        return ResponseEntity.ok("success");
    }




    @RequestMapping(value = "/getUrlFiles",method = RequestMethod.GET)
    public ResponseEntity<String> getUrlfiles(){
        return new ResponseEntity((new MongoDb().getUrlFiles()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFiles",method = RequestMethod.GET)
    public ResponseEntity<?> getFiles(@RequestParam("collection")String collection){
        return new ResponseEntity((new MongoDb().getAllFilesInCollection(collection)), HttpStatus.OK);
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

