package com.sharingnotes.Controller;

import com.google.gson.Gson;
import com.sharingnotes.Model.Chat;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@CrossOrigin()
@RestController
public class ChatController {

    public MongoDb mongo=new MongoDb();
    private static final Gson gson = new Gson();

    @RequestMapping(value = "/createChat",method = RequestMethod.POST)
    public ResponseEntity<String> createChat(@RequestParam("id_user1")String user1,@RequestParam("id_user2") String user2){
        mongo.createChat(new Chat(UUID.randomUUID(),user1,user2));
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/getAllChat",method = RequestMethod.GET)
    public ResponseEntity getChat(@RequestParam("id_user1")String user1){
        return ResponseEntity.ok(mongo.getChatWithUser(user1));
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    public ResponseEntity<String> sendMessage(@RequestParam("id_user1")String user1,@RequestParam("id_user2")String user2,@RequestParam("sender")boolean sender,@RequestParam("messaggio") String messaggio){
        HashMap<String,String> map=new HashMap<>();

        //se user1 è il sender allora invia il suo id come chiave del messaggio altrimenti lo farà user2
        if(sender) {
            map.put(user1, messaggio);
        }else{
            map.put(user2,messaggio);
        }

        Chat chat=mongo.getChat(user1, user2);
        mongo.sendMessage(chat,map);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "createGroupChat", method = RequestMethod.POST)
    public ResponseEntity<String> createGroupChat(@RequestParam("id") String ...id){
        String[] array = new String[id.length];
        int index=0;
        for (String i:id) {
            array[index++]=i;
        }
        mongo.createGroupChat(array);
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "getGroupChat",method = RequestMethod.GET)
    public ResponseEntity getGroupChat(@RequestParam("id")String id){
        ArrayList arrayList=new ArrayList();
        arrayList = mongo.getGroupChat(id);

        if (arrayList.isEmpty()){
            return new ResponseEntity<>(gson.toJson("id errato"),HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(gson.toJson(arrayList.toString()));
        }
    }

}
