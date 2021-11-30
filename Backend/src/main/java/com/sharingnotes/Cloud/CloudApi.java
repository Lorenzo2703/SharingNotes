package com.sharingnotes.Cloud;

import com.sharingnotes.Model.Notes;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.io.File;
import java.util.UUID;

public class CloudApi {

    /***
     * Funzione di caricamento file sulla piattaforma, tramite la libreria java 'Unirest'
     * e di inserimento della Nota sul database di Mongo
     * @param file
     * @param title
     * @param description
     * @param id_User
     * @param categoria
     */
    public static void uploadFile(File file,String title, String description, String id_User, String categoria){
        MongoDb mongoDb=new MongoDb();
        HttpResponse response=Unirest.post("http://127.0.0.1:5000/upload")
                .socketTimeout(6000000)
                .connectTimeout(6000000)
                .field("file",file)
                .asString();

        mongoDb.insertNotes(new Notes((UUID.randomUUID()),title, description,response.getBody().toString(),id_User, categoria));
    }
}
