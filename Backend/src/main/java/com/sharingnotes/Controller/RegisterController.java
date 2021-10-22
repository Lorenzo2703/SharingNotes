package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import com.sharingnotes.Security.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@CrossOrigin()
@RestController
public class RegisterController {
    private static final Gson gson = new Gson();
    public MongoDb mongo=new MongoDb();

    @PostMapping("/register")
    public ResponseEntity register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password){
        try{
            User user=new User((UUID.randomUUID()),name,email.toLowerCase(),password);
            if(mongo.usedName(name) == false && mongo.usedEmail(email) == false) {
                mongo.insertUser(user);
            }
            Login login =new Login();
            if (login.authentication(email.toLowerCase(), password)){
                return ResponseEntity.ok(mongo.getUser(email.toLowerCase(),password));
            }else{
                return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson("Registration error, Please retry :("), HttpStatus.BAD_REQUEST);
        }
    }
}
