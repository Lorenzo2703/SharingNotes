package com.sharingnotes.Controller;

import com.sharingnotes.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin()
@RestController
@RequestMapping("/file")
public class FileController {

    /***
     * Definisco la dipendenza
     */
    @Autowired
    private FileService fileService;

    /**
     * Upload del pdf su Mega e inserimento della nota nel
     * component Mongo
     * @param multipartFile
     * @param title
     * @param description
     * @param id
     * @param categoria
     * @return
     */
    @PostMapping("/fileUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userId") String id, @RequestParam("categoria") String categoria) {
        return fileService.uploadFile(multipartFile, title, description, id, categoria);
    }

    /**
     * get dell'url di mega del file
     * @return
     */
    @GetMapping("/getUrlFiles")
    public ResponseEntity<String> getUrlfiles(){
        return fileService.getUrlfiles();
    }

    /**
     * get di tutti i document presenti nella collection di mongo
     * @param collection
     * @return
     */
    @GetMapping("/getFiles")
    public ResponseEntity getFiles(@RequestParam("collection")String collection){
        return fileService.getFiles(collection);
    }

    /**
     * elimina il document dalla collection
     * @param id
     * @param collection
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam("_id") String id, @RequestParam("collection")String collection){
        return fileService.delete(id,collection);
    }
}
