package com.sharingnotes.Cloud;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
