package com.sharingnotes;

import com.sharingnotes.Cloud.CloudApi;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
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
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        CloudApi.uploadFile(convertFile(multipartFile));
        return ResponseEntity.ok("Success");
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

