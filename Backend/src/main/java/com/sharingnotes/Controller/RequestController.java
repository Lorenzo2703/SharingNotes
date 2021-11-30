package com.sharingnotes.Controller;

import com.sharingnotes.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/request")
public class RequestController {

    /***
     * Definisco la dipendenza
     */
    @Autowired
    private RequestService requestService;

    /**
     * Crea una nuova richiesta
     * @param idRichiedente
     * @param title
     * @param testo
     * @return
     */
    @PostMapping("/insertRichiesta")
    public ResponseEntity<String> insertRichiesta(@RequestParam("idRichiedente") String idRichiedente, @RequestParam("title") String title, @RequestParam("testo") String testo){
        return requestService.insertRichiesta(idRichiedente, title, testo);
    }

    /**
     * Richiesta passa da aperta a completata
     * @param bool
     * @param id
     * @param collection
     * @return
     */
    @PostMapping("/completeRequest")
    public ResponseEntity<String> completeRequest(@RequestParam("bool") String bool, @RequestParam("id")String id,@RequestParam("collection") String collection){
        return requestService.completeRequest(bool,id,collection);
    }
}
