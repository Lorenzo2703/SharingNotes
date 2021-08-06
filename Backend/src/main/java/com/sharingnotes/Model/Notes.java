package com.sharingnotes.Model;

import java.util.UUID;

public class Notes {
    private UUID _id;
    private String title;
    private String description;
    private int rating;
    private String fileUrl;
    private String id_User;
    private int nVoti;

    public Notes(UUID _id, String title, String description, String fileUrl, String id_User) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.rating = 0;
        this.fileUrl = fileUrl;
        this.id_User = id_User;
        this.nVoti = 0 ;
    }

    public UUID getId() {
        return _id;
    }

    public void setId(UUID id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public int getnVoti() {
        return nVoti;
    }

    public void setnVoti(int nVoti) {
        this.nVoti = nVoti;
    }

}
