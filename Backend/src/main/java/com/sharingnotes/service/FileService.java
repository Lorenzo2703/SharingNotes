package com.sharingnotes.service;

import com.sharingnotes.Cloud.CloudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import kong.unirest.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {



    public String getAll() {
        return CloudApi.getAll().toString();
    }


    public File convertFile(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : null;
        File file;
        try {
            file = File.createTempFile(fileName.substring(0,fileName.lastIndexOf("."))
                    .replaceAll("[^a-zA-Z0-9-_\\.]", "_"), prefix);
            multipartFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
