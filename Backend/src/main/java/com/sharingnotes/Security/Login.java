package com.sharingnotes.Security;

import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;

public class Login {

    public boolean authentication(String email,String password){
        try {
            Document user = new MongoDb().getUser(email.toLowerCase(), password, "utenti");
            System.out.println(user.get("name"));
            return user != null;
        }catch (Exception e){
            return false;
        }
    }
}
