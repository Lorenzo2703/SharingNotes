package com.sharingnotes.Cloud;

import com.sharingnotes.Model.Notes;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public static File download(String fileUrl){
        HttpResponse<String> response=Unirest.get("http://127.0.0.1:5000/download?fileurl="+fileUrl.replace("#","$"))
                .header("Content-Type", "application/octet-stream")
                .asString();

        File tempFile = null;

        try {
            tempFile  = File.createTempFile("info-", "-temp");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            bw.write(response.getBody());
            bw.close();
            tempFile.deleteOnExit();
        }catch (Exception e){
            e.printStackTrace();
        }

        return tempFile;
    }

}
