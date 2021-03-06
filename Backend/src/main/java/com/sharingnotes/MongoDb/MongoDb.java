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
    static MongoDb mongo=null;

    /***
     * Costruttore della connessione al cluster di Mongo
     */
    public MongoDb() {
        connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0.xxygs.mongodb.net/test?retryWrites=true&w=majority");
        settings = MongoClientSettings.builder().uuidRepresentation(UuidRepresentation.JAVA_LEGACY).applyConnectionString(connectionString).build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
    }

    /***
     * Richiamo la connessione al cluster di Mongo
     * @return
     */
    public static MongoDb getConnection() {
        if (mongo == null) {
             mongo= new MongoDb();
        }
        return mongo;
    }

    /***
     * Funzione di eliminazione di un documento
     * @param document
     * @param collection
     */
    public void deleteDocument(Document document,String collection){
        database.getCollection(collection).deleteOne(document);
    }

    /***
     * Funzione di eliminazione di tutte le recensioni interne a una nota eliminata
     * @param idNota
     */
    public void deleteReviews(String idNota){
        database.getCollection("recensioni").deleteMany(Filters.eq("id_Nota_Recensita",idNota));
    }

    /***
     * Funzione che spunta il completamento di una richiesta
     * @param document
     * @param collection
     * @param bool
     */
    public void completeRequest(Document document,String collection,boolean bool){
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("completed",bool));
    }

    /***
     * Funzione che effettua la media tra il voto inserito e i voti gi??
     * presenti e aggiorna il database di Mongo
     * @param document
     * @param collection
     * @param score
     */
    public void updateScore(Document document,String collection,int score){
        int newNVoti=(document.getInteger("nvoti")+1);
        int newSomma=(document.getInteger("sommaVoti")+score);
        float media = Math.round(newSomma/ newNVoti);

        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("nvoti",newNVoti));
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("sommaVoti",newSomma));
        database.getCollection(collection).updateOne(Filters.eq("_id",document.get("_id")), Updates.set("rating",media));

    }

    /***
     * Funzione che inserisce un utente nel database di Mongo
     * @param user
     */
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

    /***
     * Funzione che passati email e password ritorna il documento dell'utente
     * @param email
     * @param password
     * @return
     */
    public Document getUser(String email, String password){
        MongoCollection<Document> collection = database.getCollection("utenti");
        Document user = collection.find(and(eq("email", email.toLowerCase()),eq("password", password))).first();
        return user;
    }

    /***
     * Funzione che verifica se un nome ?? gi?? stato utilizzato
     * @param name
     * @return
     */
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

    /***
     * Funzione che verifica se un email ?? gi?? stata utilizzata
     * @param email
     * @return
     */
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

    /***
     * Funzione che ritorna il documento mongo, passando l'id e la collection
     * @param notesID
     * @param collection
     * @return
     */
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

    /***
     * Funzione che inserisce l'id degli utenti votati
     * @param user
     * @param id_votato
     */
    public void insertIdVotati(User user,String id_votato){
        user.insertId_votati(id_votato);
        database.getCollection("utenti").updateOne((Filters.eq("_id",user.getId())), Updates.set("id_votati",user.getId_votati()));
    }

    /***
     * Funzione che inserisce una recensione nel database di Mongo
     * @param recensione
     */
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

    /***
     * Funzione che inserisce una chat nel database di Mongo
     * @param chat
     */
    public void createChat(Chat chat){
        Document createChat = new Document();
        createChat.append("_id",chat.get_id())
                .append("id_User1",chat.getId_user1())
                .append("id_User2",chat.getId_user2())
                .append("messaggio",new ArrayList<String>());

        database.getCollection("chat").insertOne(createChat);
    }

    /***
     * Funzione che inserisce un messaggio nella chat e effettua l'update nel database di Mongo
     * @param chat
     * @param messaggio
     */
    public void sendMessage(Chat chat,HashMap<String,String> messaggio){
        chat.insertMessage(messaggio);
        database.getCollection("chat").updateOne((Filters.and(eq("id_User1",chat.getId_user1()),eq("id_User2",chat.getId_user2()))), Updates.set("messaggio",chat.getMessaggi()));
    }

    /***
     * Funzione che inserisce un messaggio nella chat di gruppo e effettua l'update nel database di Mongo
     * @param chat
     * @param messaggio
     */
    public void sendGroupMessage(GroupChat chat,HashMap<String,String> messaggio){
        chat.insertMessage(messaggio);
        database.getCollection("group-chat").updateOne((Filters.and(eq("_id",chat.get_id()))), Updates.set("messaggi",chat.getMessaggi()));
    }

    /***
     * Funzione che restituisce la chat di gruppo in base all'id
     * @param id
     * @return
     */
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

    /***
     * Funzione che restituisce la chat di gruppo in base all'id dei due utenti
     * @param idUser1
     * @param idUser2
     * @return
     */
    public Chat getChat(String idUser1, String idUser2){
        Document document=database.getCollection("chat").find((and(eq("id_User1",idUser1),eq("id_User2",idUser2)))).first();
        Chat chat= new Chat(UUID.fromString(document.get("_id").toString()),document.get("id_User1").toString(),document.get("id_User2").toString(),(ArrayList)(document.get("messaggio")));
        return chat;
    }

    /***
     * Funzione che restituisce tutte le chat dove l'id dell'user ?? presente
     * @param idUser1
     * @return
     */
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

    /***
     * Funzione che crea la chat di gruppo
     * @param name
     * @param id
     */
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

    /***
     * Funzione che restituisce la chat di gruppo dove ??'id dell'utente ?? presente
     * @param id
     * @return
     */
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

    /***
     * Funzione che inserisce una richiesta nel database di Mongo
     * @param richiesta
     */
    public void insertRichiesta(Richiesta richiesta){
        Document insertRichiesta = new Document();
        insertRichiesta.append("_id",richiesta.get_id())
                .append("id_Richiedente",richiesta.getIdRichiedente())
                .append("title",richiesta.getTitle())
                .append("testo",richiesta.getTesto())
                .append("completed",richiesta.isCompleted());

        database.getCollection("richieste").insertOne(insertRichiesta);
    }

    /***
     * Funzione che inserisce una nota nel database di Mongo
     * @param notes
     */
    public void insertNotes(Notes notes){
        Document insertNotes = new Document();
        insertNotes.append("_id",notes.getId())
                .append("title",notes.getTitle())
                .append("description",notes.getDescription())
                .append("rating",notes.getRating())
                .append("nvoti",notes.getnVoti())
                .append("fileUrl",notes.getFileUrl())
                .append("id_User", notes.getId_User())
                .append("categoria", notes.getCategoria())
                .append("sommaVoti", notes.getSommaVoti());

        database.getCollection("notes").insertOne(insertNotes);
    }

    /***
     * Funzione che restituisce la lista di tutti gli Url delle note inserite
     * @return
     */
    public ArrayList<String> getUrlFiles(){
        ArrayList<String> list = new ArrayList();
        MongoCursor<Document> cursor = database.getCollection("notes").find().iterator();
        while(cursor.hasNext()) {
            list.add(cursor.next().get("fileUrl").toString());
        }
        return list;
    }

    /***
     * Funzione che restituisce tutti i Documenti in una collection
     * @param collection
     * @return
     */
    public ArrayList getAllFilesInCollection(String collection){
        FindIterable<Document> documents=database.getCollection(collection).find();
        ArrayList arrayList=new ArrayList();
        for (Document document : documents) {
            arrayList.add(document);
        }
        return arrayList;
    }
}
