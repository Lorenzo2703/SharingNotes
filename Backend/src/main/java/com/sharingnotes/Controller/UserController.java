package com.sharingnotes.Controller;

import com.sharingnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UserController {

    /***
     * Definisco la dipendenza
     */
    @Autowired
    private UserService userService;

    /**
     * Ritorna il document dell'user cercato in base all'id
     * @param id
     * @return
     */
    @GetMapping("/getUserByID")
    public ResponseEntity getUserByID(@RequestParam("_id") String id){
        return userService.getUserByID(id);
    }

}
