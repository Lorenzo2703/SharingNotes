package com.sharingnotes.Controller;

import com.sharingnotes.Cloud.CloudApi;
import com.sharingnotes.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;


    /**
     *Aiuto al completamento del nome della località , mostra solo i primi 10
    **/
    @GetMapping("/getAllFile")
    public String getAll(){
        return fileService.getAll();
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userId") String id, @RequestParam("categoria") String categoria) {
        CloudApi.uploadFile(fileService.convertFile(multipartFile),title,description,id, categoria);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<String> download(@RequestParam("fileUrl")String fileUrl) {
        return new ResponseEntity<>(gson.toJson(CloudApi.download(fileUrl)), HttpStatus.OK);
    }
    @RequestMapping(value = "/getUrlFiles",method = RequestMethod.GET)
    public ResponseEntity<String> getUrlfiles(){
        return new ResponseEntity((mongo.getUrlFiles()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFiles",method = RequestMethod.GET)
    public ResponseEntity getFiles(@RequestParam("collection")String collection){
        return new ResponseEntity((mongo.getAllFilesInCollection(collection)), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseEntity delete(@RequestParam("_id") String id, @RequestParam("collection")String collection){
        mongo.deleteDocument(mongo.getDocumentByID(id, collection),collection);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }
}
