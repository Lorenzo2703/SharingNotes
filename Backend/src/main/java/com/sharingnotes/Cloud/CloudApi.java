package com.sharingnotes.Cloud;

import com.sharingnotes.Model.Notes;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import java.io.File;
import java.util.UUID;

public class CloudApi {

    public static void uploadFile(File file,String title, String description, String id_User, String categoria){
        MongoDb mongoDb=new MongoDb();
        HttpResponse response=Unirest.post("http://127.0.0.1:5000/upload")
                .field("file",file)
                .asString();
        mongoDb.insertNotes(new Notes((UUID.randomUUID()),title, description,response.getBody().toString(),id_User, categoria));
    }

    public static JsonNode getAll(){
        HttpResponse<JsonNode> response = Unirest.get("http://127.0.0.1:5000/getAll")
                .header("accept", "application/json")
                .asJson();
        return response.getBody();
    }

    public static Object download(String fileUrl){
        HttpResponse response=Unirest.get("http://127.0.0.1:5000/download?fileurl="+fileUrl.replace("#","$"))
                .asString();
        return  response.getBody();
    }

}
