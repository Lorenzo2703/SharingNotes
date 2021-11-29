package com.sharingnotes.Controller;

import com.sharingnotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insertUser")
    public ResponseEntity<String> insertUser(){
        return userService.insertUser();
    }

    @GetMapping("/getUserByID")
    public ResponseEntity getUserByID(@RequestParam("_id") String id){
        return userService.getUserByID(id);
    }

}
