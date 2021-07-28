package com.sharingnotes.Model;

import java.util.UUID;

public class User {
    private UUID _id;
    private String name;
    private String email;
    private String password;
    private int rating;
    private int nVoti;

    public User(UUID _id, String name, String email, String password) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = 0;
        this.nVoti = 0;
    }

    @Override
    public String toString() {
        return ("id:"+ _id+"\n"+"name:"+ name+"\n"+"email:"+ email+"\n"+"password:"+ password+"\n"+"rating:"+ rating+"\n"+"nvoti:"+ nVoti+"\n");
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

    public int getnVoti() {
        return nVoti;
    }

    public void setnVoti(int nVoti) {
        this.nVoti = nVoti;
    }

    private void updateRating (int newVoto){
        int sumVoti = this.rating*this.nVoti;
        sumVoti += newVoto;
        this.nVoti++;
        this.rating= (sumVoti/nVoti);
    }
}
