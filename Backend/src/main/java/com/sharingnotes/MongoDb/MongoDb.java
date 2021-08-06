package com.sharingnotes.MongoDb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.sharingnotes.Model.*;
import org.bson.Document;
import org.bson.UuidRepresentation;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import static com.mongodb.client.model.Filters.*;

public class MongoDb {

    ConnectionString connectionString;
    MongoClientSettings settings;
    MongoClient mongoClient;
    MongoDatabase database;

    public MongoDb() {
        connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.xxygs.mongodb.net/test?retryWrites=true&w=majority");
        settings = MongoClientSettings.builder().uuidRepresentation(UuidRepresentation.JAVA_LEGACY).applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
    }

    public void createCollection(String name) {
        database.createCollection(name);
    }

    public void deleteDocument(Document document,String collection){
        database.getCollection(collection).deleteOne(document);
    }

    public void updateScore(Document document,String collection,int score){
        int newNoti=(document.getInteger("nvoti")+1);
        int newRating=(document.getInteger("rating")+score);
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("nvoti",newNoti));
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("rating",newRating));
    }

    public void insertUser(User user){
        Document insertUser = new Document();
        insertUser.append("_id",user.getId())
                .append("name",user.getName())
                .append("email",user.getEmail().toLowerCase())
                .append("password",user.getPassword())
                .append("rating",user.getRating());
        database.getCollection("utenti").insertOne(insertUser);
    }

    public Document getUser(String email, String password, String collections){
        MongoCollection<Document> collection = database.getCollection(collections);
        Document user = collection.find(and(eq("email", email.toLowerCase()),eq("password", password))).first();
        return user;
    }

    public void insertRecensione(Recensione recensione){
        Document insertRecensione = new Document();
        insertRecensione.append("_id",recensione.getId())
                        .append("id_Recensore",recensione.getIdRecensore())
                        .append("id_Nota_Recensita",recensione.getIdNotaRecensita())
                        .append("id_User_Recensito",recensione.getIdUserRecensito())
                        .append("title",recensione.getTitle())
                        .append("testo",recensione.getTesto())
                        .append("score",recensione.getScore());

        database.getCollection("recensioni").insertOne(insertRecensione);
    }

    public void createChat(Chat chat, HashMap<String,String> messaggio){
        Document createChat = new Document();
        createChat.append("_id",chat.get_id())
                .append("id_User1",chat.getId_user1())
                .append("id_User2",chat.getId_user2())
                .append("messaggio",chat.insertMessage(messaggio));

        database.getCollection("chat").insertOne(createChat);
    }

    public void sendMessage(Chat chat,HashMap<String,String> messaggio){
        chat.insertMessage(messaggio);
        database.getCollection("chat").updateOne((Filters.and(eq("id_User1",chat.getId_user1()),eq("id_User2",chat.getId_user2()))), Updates.set("messaggio",chat.getMessaggi()));
    }

    public Chat getChat(String idUser1, String idUser2){
        Document document=database.getCollection("chat").find((and(eq("id_User1",idUser1),eq("id_User2",idUser2)))).first();
        Chat chat= new Chat(UUID.fromString(document.get("_id").toString()),document.get("id_User1").toString(),document.get("id_User2").toString(),(ArrayList)(document.get("messaggio")));
        return chat;
    }

    public void insertRichiesta(Richiesta richiesta){
        Document insertRichiesta = new Document();
        insertRichiesta.append("_id",richiesta.get_id())
                .append("id_Richiedente",richiesta.getIdRichiedente())
                .append("title",richiesta.getTitle())
                .append("testo",richiesta.getTesto())
                .append("completed",richiesta.isCompleted());

        database.getCollection("richieste").insertOne(insertRichiesta);

    }

    public void insertNotes(Notes notes){
        Document insertNotes = new Document();
        insertNotes.append("_id",notes.getId())
                .append("title",notes.getTitle())
                .append("description",notes.getDescription())
                .append("rating",notes.getRating())
                .append("fileUrl",notes.getFileUrl())
                .append("id_User", notes.getId_User());

        database.getCollection("notes").insertOne(insertNotes);
    }

    public ArrayList<String> getUrlFiles(){
        ArrayList<String> list = new ArrayList();
        MongoCursor<Document> cursor = database.getCollection("notes").find().iterator();
        while(cursor.hasNext()) {
            list.add(cursor.next().get("fileUrl").toString());
        }
        return list;
    }

    public ArrayList getAllFilesInCollection(String collection){
        FindIterable<Document> documents=database.getCollection(collection).find();
        ArrayList arrayList=new ArrayList();
        for (Document document : documents) {
            arrayList.add(document);
        }
        return arrayList;
    }





}
