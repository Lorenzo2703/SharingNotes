package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Cloud.CloudApi;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
public class FileService {

    public MongoDb mongo= new MongoDb();
    private static final Gson gson = new Gson();

    public ResponseEntity<String> getAll() {
        return new ResponseEntity<String>(gson.toJson(CloudApi.getAll().toString()), HttpStatus.OK);
    }

    public ResponseEntity<String> uploadFile(MultipartFile multipartFile,String title,String description, String id, String categoria) {
        CloudApi.uploadFile(convertFile(multipartFile),title,description,id, categoria);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    public ResponseEntity<String> download(String fileUrl) {
        return new ResponseEntity<>(gson.toJson(CloudApi.download(fileUrl)), HttpStatus.OK);
    }

    public ResponseEntity<String> getUrlfiles(){
        return new ResponseEntity((mongo.getUrlFiles()), HttpStatus.OK);
    }

    public ResponseEntity getFiles(String collection){
        return new ResponseEntity((mongo.getAllFilesInCollection(collection)), HttpStatus.OK);
    }

    public ResponseEntity delete( String id,String collection){
        mongo.deleteDocument(mongo.getDocumentByID(id, collection),collection);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
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
