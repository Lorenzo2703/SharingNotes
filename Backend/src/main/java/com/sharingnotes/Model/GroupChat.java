package com.sharingnotes.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GroupChat {
    private UUID _id;
    private ArrayList<String> id_user;
    private ArrayList<HashMap<String, String>> messaggi;

    public GroupChat(UUID _id, ArrayList<HashMap<String, String>> messaggi) {
        this._id = _id;
        this.messaggi = messaggi;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public ArrayList<String> getId_user() {
        return id_user;
    }

    public void setId_user(ArrayList<String> id_user) {
        this.id_user = id_user;
    }

    public ArrayList<HashMap<String, String>> getMessaggi() {
        return messaggi;
    }

    public ArrayList<HashMap<String, String>> insertMessage(HashMap<String, String> messaggio) {
        this.messaggi.add(messaggio);
        return messaggi;
    }
}
