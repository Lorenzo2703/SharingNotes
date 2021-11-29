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

    public MongoDb mongo= MongoDb.getConnection();
    private static final Gson gson = new Gson();

    public ResponseEntity<String> createChat(String user1, String user2) {
        mongo.createChat(new Chat(UUID.randomUUID(),user1,user2));
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }

    public ResponseEntity<ArrayList> getChat(String user1) {
        return ResponseEntity.ok(mongo.getChatWithUser(user1));
    }

    public ResponseEntity<String> sendGroupMessage(String id, String user,String messaggio){
        HashMap<String,String> map=new HashMap<>();
        map.put(user, messaggio);
        GroupChat chat = mongo.getGroupChatByID(id);
        mongo.sendGroupMessage(chat,map);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity getGroupChat(String id){
        ArrayList arrayList=new ArrayList();
        arrayList = mongo.getGroupChat(id);

        if (arrayList.isEmpty()){
            return new ResponseEntity<>(gson.toJson("id errato"),HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(gson.toJson(arrayList));
        }
    }

    public ResponseEntity<String> createGroupChat(String name, String ...id){
        String[] array = new String[id.length];
        int index=0;
        for (String i:id) {
            array[index++]=i;
        }
        mongo.createGroupChat(name, array);
        return ResponseEntity.ok("success");
    }

    public ResponseEntity<String> sendMessage(String user1, String user2,boolean sender, String messaggio){

        HashMap<String,String> map=new HashMap<>();

        //se user1 è il sender allora invia il suo id come chiave del messaggio altrimenti lo farà user2
        if(sender) {
            map.put(user1, messaggio);
        }else{
            map.put(user2,messaggio);
        }

        Chat chat=mongo.getChat(user1, user2);
        mongo.sendMessage(chat,map);

        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);

    }
}
