package com.sharingnotes.service;

import com.google.gson.Gson;
import com.sharingnotes.Model.Chat;
import com.sharingnotes.Model.GroupChat;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ChatService {

    /***
     * Richiamo la connessione a Mongo
     */
    public MongoDb mongo= MongoDb.getConnection();
    /***
     * creazione di un Gson
     */
    private static final Gson gson = new Gson();

    /***
     * richiamo il metedo in MongoDb che crea una nuova chat singola
     * @param user1
     * @param user2
     * @return
     */
    public ResponseEntity<String> createChat(String user1, String user2) {
        mongo.createChat(new Chat(UUID.randomUUID(),user1,user2));
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    /***
     * Restituisce tutte le chat in cui nella collection mongo in
     * cui è presente l'user richiamando il metodo in mongo
     * @param user1
     * @return
     */
    public ResponseEntity<ArrayList> getChat(String user1) {
        return ResponseEntity.ok(mongo.getChatWithUser(user1));
    }

    /***
     * Invio un messaggio nella chat di gruppo
     * @param id
     * @param user
     * @param messaggio
     * @return
     */
    public ResponseEntity<String> sendGroupMessage(String id, String user,String messaggio){
        //creo l'hasmap che andrò ad inserire
        HashMap<String,String> map=new HashMap<>();
        map.put(user, messaggio);
        //get della chat di gruppo in cui sto scrivendo
        GroupChat chat = mongo.getGroupChatByID(id);
        //salvo il messaggio nella collection
        mongo.sendGroupMessage(chat,map);
        return ResponseEntity.ok("success");
    }

    /**
     * Restituisce le chat di gruppo di cui l'user fa parte
     * @param id
     * @return
     */
    public ResponseEntity getGroupChat(String id){
        //riempio l'arraylist delle chat di gruppo
        ArrayList arrayList=new ArrayList();
        arrayList = mongo.getGroupChat(id);
        /* se l'arraylist è vuoto vuol dire che l'id è sbagliato
        oppure che l'utente non ha chat di gruppo*/
        if (arrayList.isEmpty()){
            return new ResponseEntity<>(gson.toJson("id errato"),HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(gson.toJson(arrayList));
        }
    }

    /***
     * Creazione della chat di gruppo
     * @param name
     * @param id
     * @return
     */
    public ResponseEntity<String> createGroupChat(String name, String ...id){
        //inizializzo l'array che andrò a riempire con gli id degli utenti della chat
        String[] array = new String[id.length];
        int index=0;
        for (String i:id) {
            array[index++]=i;
        }
        //creo la nuova chat
        mongo.createGroupChat(name, array);
        return ResponseEntity.ok("success");
    }

    /***
     * Inserisce il messaggio nella colletion
     * @param user1
     * @param user2
     * @param sender
     * @param messaggio
     * @return
     */
    public ResponseEntity<String> sendMessage(String user1, String user2,boolean sender, String messaggio){
        //creo l'hashmap che andrò ad inserire nel component
        HashMap<String,String> map=new HashMap<>();
        //se user1 è il sender allora invia il suo id come chiave del messaggio altrimenti lo farà user2
        if(sender) {
            map.put(user1, messaggio);
        }else{
            map.put(user2,messaggio);
        }
        //prendo la chat in cui devo inviare il messaggio
        Chat chat=mongo.getChat(user1, user2);
        //salvo il messaggio nella collection
        mongo.sendMessage(chat,map);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }
}
