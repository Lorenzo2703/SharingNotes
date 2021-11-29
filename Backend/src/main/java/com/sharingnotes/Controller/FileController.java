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

    @Autowired
    private FileService fileService;

    /***
     *
     * @return
     */
    @GetMapping("/getAllFile")
    public ResponseEntity<String> getAll(){
        return fileService.getAll();
    }

    @PostMapping("/fileUpload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userId") String id, @RequestParam("categoria") String categoria) {
        return fileService.uploadFile(multipartFile, title, description, id, categoria);
    }

    @GetMapping("/download")
    public ResponseEntity<String> download(@RequestParam("fileUrl")String fileUrl) {
        return fileService.download(fileUrl);
    }

    @GetMapping("/getUrlFiles")
    public ResponseEntity<String> getUrlfiles(){
        return fileService.getUrlfiles();
    }

    @GetMapping("/getFiles")
    public ResponseEntity getFiles(@RequestParam("collection")String collection){
        return fileService.getFiles(collection);
    }

    @GetMapping("/delete")
    public ResponseEntity delete(@RequestParam("_id") String id, @RequestParam("collection")String collection){
        return fileService.delete(id,collection);
    }
}
