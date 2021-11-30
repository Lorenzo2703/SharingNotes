package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security_old.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class RegisterService {

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /**
     * Registrazione di un nuovo utente
     * @param name
     * @param email
     * @param password
     * @return
     */
    public ResponseEntity register(String name, String email, String password){
        try{
            Login login =new Login();
            //verifico che non esista già un utente con quella mail e quella password
            if (!login.authentication(email.toLowerCase(), password)){
                //creo un nuovo user e verifico che il nome o la mail non siano già state utilizzate
                User user=new User((UUID.randomUUID()),name,email.toLowerCase(),password, new ArrayList<>());
                if(mongo.usedName(name) == false && mongo.usedEmail(email) == false) {
                    mongo.insertUser(user);
                }
                //se tutto va bene ritorno l'utente
                if (login.authentication(email.toLowerCase(), password)){
                    return ResponseEntity.ok(mongo.getUser(email.toLowerCase(),password));
                }else{
                    return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
        }
    }
}
