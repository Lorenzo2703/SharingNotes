package com.sharingnotes.Controller;

import com.sharingnotes.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/recensione")
public class ReviewController {

    /***
     * Definisco la dipendenza
     */
    @Autowired
    private ReviewService reviewService;

    /**
     * Inserirsco una nuova recensione della nota
     * @param idRecensore
     * @param idUserRecensito
     * @param idNotaRecensita
     * @param title
     * @param testo
     * @return
     */
    @PostMapping( "/insertRecensione")
    public ResponseEntity<String> insertRecensione(@RequestParam("idRecensore") String idRecensore, @RequestParam("idUserRecensito") String idUserRecensito, @RequestParam("idNotaRecensita") String idNotaRecensita, @RequestParam("title") String title, @RequestParam("testo") String testo){
        return reviewService.insert(idRecensore, idUserRecensito, idNotaRecensita, title, testo);
    }
}
