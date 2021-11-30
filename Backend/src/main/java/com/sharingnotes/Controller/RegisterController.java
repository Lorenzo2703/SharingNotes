package com.sharingnotes.Controller;

import com.sharingnotes.service.LoginService;
import com.sharingnotes.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/register")
public class RegisterController {

    /***
     * Definisco la dipendenza
     */
    @Autowired
    private RegisterService registerService;

    /**
     * Registrazione di un nuovo utente
     * @param name
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password){
        return registerService.register(name, email, password);
    }
}
