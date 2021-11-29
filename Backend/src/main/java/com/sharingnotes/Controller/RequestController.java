package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;
    public MongoDb mongo= new MongoDb();
    private static final Gson gson = new Gson();

    @PostMapping("/insertRichiesta")
    public ResponseEntity<String> insertRichiesta(@RequestParam("idRichiedente") String idRichiedente, @RequestParam("title") String title, @RequestParam("testo") String testo){
        return requestService.insertRichiesta(idRichiedente, title, testo);
    }

    @PostMapping("/completeRequest")
    public ResponseEntity<String> completeRequest(@RequestParam("bool") String bool, @RequestParam("id")String id,@RequestParam("collection") String collection){
        return requestService.completeRequest(bool,id,collection);
    }
}
