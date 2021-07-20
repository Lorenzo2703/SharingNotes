package com.sharingnotes.MongoDb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import org.bson.Document;

public class MongoDb {

    ConnectionString connectionString;
    MongoClientSettings settings;
    MongoClient mongoClient;
    MongoDatabase database;

    public MongoDb() {
        connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.c1nrg.mongodb.net/test?retryWrites=true&w=majority");
        settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
    }

    public void createCollection(String name) {
        database.createCollection(name);
    }

    public void insertUser(User user,String collection){
        Document insertUser = new Document();
        insertUser.append("id",user.getId())
                .append("name",user.getName())
                .append("email",user.getEmail())
                .append("password",user.getPassword())
                .append("rating",user.getRating());
        database.getCollection(collection).insertOne(insertUser);
    }

    public void insertNotes(Notes notes, String collection){
        Document insertNotes = new Document();
        insertNotes.append("id",notes.getId())
                .append("title",notes.getTitle())
                .append("description",notes.getDescription())
                .append("thumbnail",notes.getThumbnail())
                .append("rating",notes.getRating())
                .append("fileUrl",notes.getFileUrl());

        database.getCollection(collection).insertOne(insertNotes);
    }

}
