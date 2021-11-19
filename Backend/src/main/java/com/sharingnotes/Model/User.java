package com.sharingnotes.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {
    private UUID _id;
    private String name;
    private String email;
    private String password;
    private int rating;
    private int nvoti;
    private int sommaVoti;
    private ArrayList<String> id_votati;

    public User(UUID _id, String name, String email, String password, ArrayList<String> id_votati) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = 0;
        this.nvoti = 0;
        this.sommaVoti = 0;
        this.id_votati = id_votati;
    }

    @Override
    public String toString() {
        return ("id:"+ _id+"\n"+"name:"+ name+"\n"+"email:"+ email+"\n"+"password:"+ password+"\n"+"rating:"+ rating+"\n"+"nvoti:"+ nvoti+"\n"+"sommaVoti:"+ sommaVoti+"\n");
    }

    public UUID getId() {
        return _id;
    }

    public void setId(UUID id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNvoti() {
        return nvoti;
    }

    public void setNvoti(int nvoti) {
        this.nvoti = nvoti;
    }

    public int getSommaVoti() {
        return sommaVoti;
    }

    public void setSommaVoti(int sommaVoti) {
        this.sommaVoti = sommaVoti;
    }

    public ArrayList<String> getId_votati() {
        return id_votati;
    }

    public void setId_votati(ArrayList<String> id_votati) {
        this.id_votati = id_votati;
    }

    public ArrayList<String> insertId_votati(String id_votato) {
        this.id_votati.add(id_votato);
        return id_votati;
    }
}
