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
import java.io.File;
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

    public void destroy(){
        mongoClient.close();
    }

    public void createCollection(String name) {
        database.createCollection(name);
    }

    public void deleteDocument(Document document,String collection){
        database.getCollection(collection).deleteOne(document);
    }

    public void completeRequest(Document document,String collection,boolean bool){
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("completed",bool));
    }

    public void updateScore(Document document,String collection,int score){
        int newNVoti=(document.getInteger("nvoti")+1);
        int newSomma=(document.getInteger("sommaVoti")+score);
        float media = Math.round(newSomma/ newNVoti);
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("nvoti",newNVoti));
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("sommaVoti",newSomma));
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("rating",media));

    }

    /*public float avgScore(Document document){
        int somma=document.getInteger("sommaVoti");
        int nvoti=document.getInteger("nvoti");
        float media=0.0f;
        try {
            media = Math.round(somma/ nvoti);
        }catch (Exception e){
            e.printStackTrace();
        }
        return media;
    }*/

    public void insertUser(User user){
        Document insertUser = new Document();
        insertUser.append("_id",user.getId())
                .append("name",user.getName())
                .append("email",user.getEmail().toLowerCase())
                .append("password",user.getPassword())
                .append("rating",user.getRating())
                .append("nvoti",user.getNvoti())
                .append("sommaVoti",user.getSommaVoti())
                .append("id_votati", user.getId_votati());
        database.getCollection("utenti").insertOne(insertUser);
    }

    public Document getUser(String email, String password){
        MongoCollection<Document> collection = database.getCollection("utenti");
        Document user = collection.find(and(eq("email", email.toLowerCase()),eq("password", password))).first();
        return user;
    }

    public boolean usedName(String name){
        FindIterable<Document> documents=database.getCollection("utenti").find();
        boolean used = false;
        for (Document document : documents) {
            if ((document.get("name").toString()).equals(name.trim())){
                used = true;
            }
        }
        return used;
    }

    public boolean usedEmail(String email){
        FindIterable<Document> documents=database.getCollection("utenti").find();
        boolean used = false;
        for (Document document : documents) {
            if ((document.get("email").toString()).equals(email.trim())){
                used = true;
            }
        }
        return used;
    }

    public Document getUserByID(String userID){
        FindIterable<Document> documents=database.getCollection("utenti").find();
        Document documento=null;
        for (Document document : documents) {
            if ((document.get("_id").toString()).equals(userID.trim())){
                documento=document;
            }
        }
        return documento;
    }

    public Document getDocumentByID(String notesID, String collection){
        FindIterable<Document> documents=database.getCollection(collection).find();
        Document documento=null;
        for (Document document : documents) {
            if ((document.get("_id").toString()).equals(notesID.trim())){
                documento=document;
            }
        }
        return documento;
    }

    public void insertIdVotati(User user,String id_votato){
        user.insertId_votati(id_votato);
        database.getCollection("utenti").updateOne((Filters.eq("_id",user.getId())), Updates.set("id_votati",user.getId_votati()));
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

    public void createChat(Chat chat){
        Document createChat = new Document();
        createChat.append("_id",chat.get_id())
                .append("id_User1",chat.getId_user1())
                .append("id_User2",chat.getId_user2())
                .append("messaggio",new ArrayList<String>());

        database.getCollection("chat").insertOne(createChat);
    }

    public void sendMessage(Chat chat,HashMap<String,String> messaggio){
        chat.insertMessage(messaggio);
        database.getCollection("chat").updateOne((Filters.and(eq("id_User1",chat.getId_user1()),eq("id_User2",chat.getId_user2()))), Updates.set("messaggio",chat.getMessaggi()));
    }

    public void sendGroupMessage(GroupChat chat,HashMap<String,String> messaggio){
        chat.insertMessage(messaggio);
        database.getCollection("group-chat").updateOne((Filters.and(eq("_id",chat.get_id()))), Updates.set("messaggi",chat.getMessaggi()));
    }

    //get della chat di gruppo in base all'ID della chat di gruppo
    public GroupChat getGroupChatByID(String id){
        FindIterable<Document> documents=database.getCollection("group-chat").find();
        Document documento=null;
        for (Document document : documents) {
            if ((document.get("_id").toString()).equals(id.trim())){
                documento=document;
            }
        }
        GroupChat chat = new GroupChat(UUID.fromString(documento.get("_id").toString()), (ArrayList<HashMap<String, String>>) documento.get("messaggi"));
        return chat;
    }

    public Chat getChat(String idUser1, String idUser2){
        Document document=database.getCollection("chat").find((and(eq("id_User1",idUser1),eq("id_User2",idUser2)))).first();
        Chat chat= new Chat(UUID.fromString(document.get("_id").toString()),document.get("id_User1").toString(),document.get("id_User2").toString(),(ArrayList)(document.get("messaggio")));
        return chat;
    }

    public ArrayList getChatWithUser(String idUser1){
        ArrayList arrayList=new ArrayList();
        FindIterable<Document> documents=database.getCollection("chat").find();
        for (Document document : documents) {
            if (document.get("id_User1").equals(idUser1)||document.get("id_User2").equals(idUser1)){
                arrayList.add(document);
            }
        }
        return arrayList;
    }

    public void createGroupChat(String name, String ...id){
        Document createGroupChat = new Document();
        int index=1;
        createGroupChat.append("_id",UUID.randomUUID());
        createGroupChat.append("name", name);
        for (String i:id) {
            createGroupChat.append(("id_"+index++),i);
        }
        createGroupChat.append("messaggi",new ArrayList<String>());
        database.getCollection("group-chat").insertOne(createGroupChat);
    }

    //get delle chat di gruppo dove l'id dell'user è presente
    public ArrayList getGroupChat(String id){
        ArrayList arrayList=new ArrayList();
        MongoCursor<Document> cursor = database.getCollection("group-chat").find().iterator();
        int index=0;

        //verifico gli id per ogni key nella collection
        while(cursor.hasNext()) {
            index+=cursor.next().size();
        }

        for (int i=0;i<index;i++){
            if (database.getCollection("group-chat").find(eq(("id_"+i),id)).first()!=null)
                database.getCollection("group-chat").find(eq(("id_"+i),id)).forEach(arrayList::add);;
        }
        return arrayList;
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
                .append("nvoti",notes.getnVoti())
                .append("fileUrl",notes.getFileUrl())
                .append("id_User", notes.getId_User())
                .append("sommaVoti", notes.getSommaVoti());

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
