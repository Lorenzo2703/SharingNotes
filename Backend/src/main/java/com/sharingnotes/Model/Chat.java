package com.sharingnotes.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Chat {
    private UUID _id;
    private String id_user1;
    private String id_user2;
    private ArrayList<HashMap<String, String>> messaggi;

    /***
     * Costruttore del chat model
     * @param _id
     * @param id_user1
     * @param id_user2
     */
    public Chat(UUID _id, String id_user1, String id_user2) {
        this._id = _id;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.messaggi = new ArrayList<HashMap<String,String>>();
    }

    /***
     * Overload del costruttore del chat model
     * @param _id
     * @param id_user1
     * @param id_user2
     */
    public Chat(UUID _id, String id_user1, String id_user2,ArrayList<HashMap<String,String>> messaggi) {
        this._id = _id;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
        this.messaggi = messaggi;
    }


    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getId_user1() {
        return id_user1;
    }

    public void setId_user1(String id_user1) {
        this.id_user1 = id_user1;
    }

    public String getId_user2() {
        return id_user2;
    }

    public void setId_user2(String id_user2) {
        this.id_user2 = id_user2;
    }

    public ArrayList<HashMap<String, String>> getMessaggi() {
        return messaggi;
    }

    public ArrayList<HashMap<String, String>> insertMessage(HashMap<String, String> messaggio) {
        this.messaggi.add(messaggio);
        return messaggi;
    }
}


