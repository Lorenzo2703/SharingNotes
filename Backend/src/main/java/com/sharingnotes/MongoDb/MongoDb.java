package com.sharingnotes.MongoDb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

public class MongoDb {

    ConnectionString connectionString;
    MongoClientSettings settings;
    MongoClient mongoClient;
    MongoDatabase database;

    public MongoDb() {
        connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.xxygs.mongodb.net/test?retryWrites=true&w=majority");
        settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
    }

    public void createCollection(String name) {
        database.createCollection(name);
    }

    public void insertUser(User user,String collection){
        Document insertUser = new Document();
        insertUser.append("_id",user.getId())
                .append("name",user.getName())
                .append("email",user.getEmail())
                .append("password",user.getPassword())
                .append("rating",user.getRating());
        database.getCollection(collection).insertOne(insertUser);
    }

    public void insertNotes(Notes notes, String collection){
        Document insertNotes = new Document();
        insertNotes.append("_id",notes.getId())
                .append("title",notes.getTitle())
                .append("description",notes.getDescription())
                .append("thumbnail",notes.getThumbnail())
                .append("rating",notes.getRating())
                .append("fileUrl",notes.getFileUrl())
                .append("id_User", notes.getId_User());

        database.getCollection(collection).insertOne(insertNotes);
    }

    public Document getUser(String email, String password, String collections){
        MongoCollection<Document> collection = database.getCollection(collections);
        Document user = collection.find(and(eq("email", email),eq("password", password))).first();
        return user;
    }

}