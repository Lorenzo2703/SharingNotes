package com.sharingnotes.Security;

import com.sharingnotes.MongoDb.MongoDb;
import org.bson.Document;

public class Login {

    /***
     * Funzione di autenticazione al portale, che effettua il match tra email e password
     * inserite, con quelle presenti nel database di Mongo
     * @param email
     * @param password
     * @return
     */
    public boolean authentication(String email,String password){
        try {
            Document user = new MongoDb().getUser(email.toLowerCase(), password);
            return user != null;
        }catch (Exception e){
            return false;
        }
    }
}
