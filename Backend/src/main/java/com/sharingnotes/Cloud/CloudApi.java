package com.sharingnotes.Cloud;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import java.io.File;

public class CloudApi {

    public static void uploadFile(File file){
        Unirest.post("http://127.0.0.1:5000/upload")
                .field("file",file)
                .asEmpty();
    }

    public static JsonNode getAll(){
        HttpResponse<JsonNode> response = Unirest.get("http://127.0.0.1:5000/getAll")
                .header("accept", "application/json")
                .asJson();
        return response.getBody();
    }

}
