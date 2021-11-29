package com.sharingnotes.Controller;

import com.sharingnotes.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping( "/createChat")
    public ResponseEntity<String> createChat(@RequestParam("id_user1")String user1,@RequestParam("id_user2") String user2) {
        return chatService.createChat(user1, user2);
    }

    @GetMapping("/getAllChat")
    public ResponseEntity getChat(@RequestParam("id_user1")String user1){
    return chatService.getChat(user1);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("id_user1")String user1,@RequestParam("id_user2")String user2,@RequestParam("sender")boolean sender,@RequestParam("messaggio") String messaggio){
        return chatService.sendMessage(user1,user2,sender,messaggio);
    }

    @RequestMapping("/sendGroupMessage")
    public ResponseEntity<String> sendGroupMessage(@RequestParam("id")String id, @RequestParam("id_user")String user,@RequestParam("messaggio") String messaggio){
        return chatService.sendGroupMessage(id,user,messaggio);
    }

    @RequestMapping(value = "createGroupChat", method = RequestMethod.POST)
    public ResponseEntity<String> createGroupChat(@RequestParam("name") String name, @RequestParam("id") String ...id){
       return chatService.createGroupChat(name,id);
    }

    @RequestMapping(value = "getGroupChat",method = RequestMethod.GET)
    public ResponseEntity getGroupChat(@RequestParam("id")String id){
        return chatService.getGroupChat(id);
    }

}
