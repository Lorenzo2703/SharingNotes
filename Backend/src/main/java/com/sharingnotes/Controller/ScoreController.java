package com.sharingnotes.Controller;

import com.sharingnotes.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @PostMapping(value = "/updateScore")
    public ResponseEntity<String> updateScore(@RequestParam("score") String score, @RequestParam("id")String id,@RequestParam("collection") String collection){
        return scoreService.updateScore(score, id, collection);
    }

    @PostMapping(value = "/insertIdVotati")
    public ResponseEntity<String> insertIdVotati(@RequestParam("id_votato") String id_votato, @RequestParam("id")String id){
       return scoreService.insertIdVotati(id_votato, id);
    }

}
