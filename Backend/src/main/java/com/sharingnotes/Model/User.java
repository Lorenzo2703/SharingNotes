package com.sharingnotes.Model;

public class User {
    private int _id;
    private String name;
    private String email;
    private String password;
    private int rating;
    private int nVoti;

    public User(int _id, String name, String email, String password) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rating = 0;
        this.nVoti = 0;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
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
